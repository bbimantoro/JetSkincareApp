package com.academy.bangkit.jetskincare.data

import com.academy.bangkit.jetskincare.model.FakeSkincareDataSource
import com.academy.bangkit.jetskincare.model.OrderSkincare
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SkincareRepository {
    private val orderSkincare = mutableListOf<OrderSkincare>()

    init {
        if (orderSkincare.isEmpty()) {
            FakeSkincareDataSource.dummySkincare.forEach {
                orderSkincare.add(OrderSkincare(it, 0))
            }
        }
    }

    fun getAllSkincare(): Flow<List<OrderSkincare>> = flowOf(orderSkincare)

    companion object {
        @Volatile
        private var instance: SkincareRepository? = null

        fun getInstance(): SkincareRepository =
            instance ?: synchronized(this) {
                SkincareRepository().apply {
                    instance = this
                }
            }
    }
}