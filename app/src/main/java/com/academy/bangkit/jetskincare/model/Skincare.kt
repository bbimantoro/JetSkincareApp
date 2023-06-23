package com.academy.bangkit.jetskincare.model

import androidx.annotation.StringRes

data class Skincare(
    val id: Int,
    val image: Int,
    val name: String,
    @StringRes val desc: Int,
    val price: String
)