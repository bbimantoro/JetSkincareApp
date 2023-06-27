package com.academy.bangkit.jetskincare.ui.screen.cart

import com.academy.bangkit.jetskincare.model.OrderSkincare

data class CartState(
    val orderSkincare: List<OrderSkincare>,
    val totalPrice: Int
)