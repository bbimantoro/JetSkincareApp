package com.academy.bangkit.jetskincare.di

import com.academy.bangkit.jetskincare.data.SkincareRepository

object Injection {
    fun provideRepository(): SkincareRepository = SkincareRepository.getInstance()
}