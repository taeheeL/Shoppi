package com.example.shoppi.repository.category

import com.example.shoppi.model.Category

interface CategoryDataSource {

    suspend fun getCategories(): List<Category>
}