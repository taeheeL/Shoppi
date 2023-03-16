package com.example.shoppi.repository.categorydetail

import com.example.shoppi.model.CategoryDetail
import com.example.shoppi.network.ApiClient

class CategoryDetailRemoteDataSource(private val api:ApiClient): CategoryDetailDataSource {
    override suspend fun getCategoryDetail(): CategoryDetail {
        return api.getCategoryDetail()
    }
}