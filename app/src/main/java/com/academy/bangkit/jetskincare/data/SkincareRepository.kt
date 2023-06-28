package com.academy.bangkit.jetskincare.data

import com.academy.bangkit.jetskincare.model.FakeSkincareDataSource
import com.academy.bangkit.jetskincare.model.OrderSkincare
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class SkincareRepository {
    private val listSkincare = mutableListOf<OrderSkincare>()

    init {
        if (listSkincare.isEmpty()) {
            FakeSkincareDataSource.dummySkincare.forEach {
                listSkincare.add(OrderSkincare(it, 0))
            }
        }
    }

    fun getAllSkincare(): Flow<List<OrderSkincare>> = flowOf(listSkincare)

    fun getOrderSkincareById(skincareId: Long): OrderSkincare = listSkincare.first {
        it.skincare.id == skincareId
    }


    fun updateOrderSkincare(skincareId: Long, newCount: Int): Flow<Boolean> {
        val index = listSkincare.indexOfFirst { it.skincare.id == skincareId }
        val result = if (index >= 0) {
            val orderSkincare = listSkincare[index]
            listSkincare[index] =
                orderSkincare.copy(skincare = orderSkincare.skincare, count = newCount)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAddedOrderSkincare(): Flow<List<OrderSkincare>> {
        return getAllSkincare().map { skincare ->
            skincare.filter {
                it.count != 0
            }
        }
    }

    fun searchSkincare(query: String): Flow<List<OrderSkincare>> {
        return getAllSkincare().map { skincare ->
            skincare.filter {
                it.skincare.name.contains(query, ignoreCase = true)
            }
        }
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