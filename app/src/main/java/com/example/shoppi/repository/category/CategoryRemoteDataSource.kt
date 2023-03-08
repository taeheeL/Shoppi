package com.example.shoppi.repository.category

import com.example.shoppi.model.Category
import com.example.shoppi.network.ApiClient

class CategoryRemoteDataSource(private val apiClient: ApiClient): CategoryDataSource {
    override suspend fun getCategories(): List<Category> {
        return apiClient.getCategories()
    }
}