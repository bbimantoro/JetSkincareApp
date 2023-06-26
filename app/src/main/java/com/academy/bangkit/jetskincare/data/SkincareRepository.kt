package com.academy.bangkit.jetskincare.data

import com.academy.bangkit.jetskincare.model.FakeSkincareDataSource
import com.academy.bangkit.jetskincare.model.OrderSkincare
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class SkincareRepository {
    private val skincare = mutableListOf<OrderSkincare>()

    init {
        if (skincare.isEmpty()) {
            FakeSkincareDataSource.dummySkincare.forEach {
                skincare.add(OrderSkincare(it, 0))
            }
        }
    }

    fun getAllSkincare(): Flow<List<OrderSkincare>> = flowOf(skincare)

    fun getOrderSkincareById(id: Int): OrderSkincare {
        return skincare.first {
            it.skincare.id == id
        }
    }

    fun updateOrderSkincare(id: Int, newCount: Int): Flow<Boolean> {
        val index = skincare.indexOfFirst { it.skincare.id == id }
        val result = if (index > 0) {
            val orderSkincare = skincare[index]
            skincare[index] =
                orderSkincare.copy(skincare = orderSkincare.skincare, count = newCount)
            true
        } else {
            false
        }
        return flowOf(result)
    }

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