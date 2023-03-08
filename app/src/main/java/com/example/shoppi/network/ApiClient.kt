package com.example.shoppi.network

import com.example.shoppi.model.Category
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory


interface ApiClient {

    @GET("categories.json")
    suspend fun getCategories(): List<Category>

    companion object {

        private const val baseUrl = "https://shoppi-3e9ac-default-rtdb.asia-southeast1.firebasedatabase.app/"

        fun create(): ApiClient {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiClient::class.java)
        }
    }
}