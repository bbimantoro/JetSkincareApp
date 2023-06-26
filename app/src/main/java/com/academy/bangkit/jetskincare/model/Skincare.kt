package com.academy.bangkit.jetskincare.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Skincare(
    val id: Int,
    val thumbnail: Int,
    val name: String,
    val desc: String,
    val price: String
)