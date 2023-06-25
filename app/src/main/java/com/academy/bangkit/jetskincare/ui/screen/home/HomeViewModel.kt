package com.academy.bangkit.jetskincare.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.bangkit.jetskincare.data.SkincareRepository
import com.academy.bangkit.jetskincare.model.OrderSkincare
import com.academy.bangkit.jetskincare.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(private val skincareRepository: SkincareRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderSkincare>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<OrderSkincare>>> get() = _uiState

    fun getAllSkincare() {
        viewModelScope.launch {
            skincareRepository.getAllSkincare()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}