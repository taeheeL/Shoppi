package com.example.shoppi.repository.category

import com.example.shoppi.model.Category

class CategoryRepository(
    private val remoteDataSource: CategoryRemoteDataSource
) {

    suspend fun getCategories(): List<Category>{
        return remoteDataSource.getCategories()
    }
}