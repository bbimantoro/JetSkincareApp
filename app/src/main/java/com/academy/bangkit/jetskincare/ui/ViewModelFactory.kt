package com.academy.bangkit.jetskincare.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.academy.bangkit.jetskincare.data.SkincareRepository
import com.academy.bangkit.jetskincare.ui.screen.cart.CartViewModel
import com.academy.bangkit.jetskincare.ui.screen.detail.DetailViewModel
import com.academy.bangkit.jetskincare.ui.screen.home.HomeViewModel

class ViewModelFactory(private val skincareRepository: SkincareRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(skincareRepository) as T
            }

            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                DetailViewModel(skincareRepository) as T
            }

            modelClass.isAssignableFrom(CartViewModel::class.java) -> {
                CartViewModel(skincareRepository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }
    }
}