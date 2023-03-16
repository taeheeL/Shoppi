package com.example.shoppi.repository.categorydetail

import com.example.shoppi.model.CategoryDetail

interface CategoryDetailDataSource {

    suspend fun getCategoryDetail():CategoryDetail

}