package com.academy.bangkit.jetskincare.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.bangkit.jetskincare.data.SkincareRepository
import com.academy.bangkit.jetskincare.model.OrderSkincare
import com.academy.bangkit.jetskincare.model.Skincare
import com.academy.bangkit.jetskincare.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val skincareRepository: SkincareRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderSkincare>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderSkincare>> get() = _uiState

    fun getSkincareById(skincareId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(skincareRepository.getOrderSkincareById(skincareId))
        }
    }

    fun addToCart(skincare: Skincare, count: Int) {
        viewModelScope.launch {
            skincareRepository.updateOrderSkincare(skincare.id, count)
        }
    }
}