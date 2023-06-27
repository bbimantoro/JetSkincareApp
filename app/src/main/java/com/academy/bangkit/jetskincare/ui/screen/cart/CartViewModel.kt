package com.academy.bangkit.jetskincare.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.academy.bangkit.jetskincare.data.SkincareRepository
import com.academy.bangkit.jetskincare.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CartViewModel(
    private val skincareRepository: SkincareRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CartState>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CartState>> get() = _uiState

    fun getAddedOrderSkincare() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            skincareRepository.getAddedOrderSkincare()
                .collect { skincare ->
                    val totalPrice = skincare.sumOf { it.skincare.price.toInt() * it.count }
                    _uiState.value = UiState.Success(CartState(skincare, totalPrice))
                }
        }
    }

    fun updateOrderSkincare(id: Int, count: Int) {
        viewModelScope.launch {
            skincareRepository.updateOrderSkincare(id, count)
                .collect { isUpdated ->
                    if (isUpdated) {
                        getAddedOrderSkincare()
                    }
                }
        }
    }
}