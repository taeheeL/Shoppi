package com.example.shoppi

import android.content.Context
import androidx.room.Room
import com.example.shoppi.database.AppDatabase
import com.example.shoppi.network.ApiClient
import com.example.shoppi.repository.cart.CartItemLocalDatasource
import com.example.shoppi.repository.cart.CartRepository


object ServiceLocator {

    private var apiClient: ApiClient? = null
    private var database: AppDatabase? = null
    private var cartRepository: CartRepository? = null

    fun provideApiClient(): ApiClient {
        return apiClient ?: kotlin.run {
            ApiClient.create().also {
                apiClient = it
            }
        }
    }

    private fun provideDatabase(applicationContext: Context): AppDatabase {
        return database ?: kotlin.run {
            Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java,
                "shoppi-local"
            ).build().also {
                database = it
            }
        }

    }

    fun provideCartRepository(context: Context): CartRepository {
        return cartRepository ?: kotlin.run {
            val dao = provideDatabase(context.applicationContext).cartItemDao()
            CartRepository(CartItemLocalDatasource(dao)).also {
                cartRepository = it
            }
        }
    }
}