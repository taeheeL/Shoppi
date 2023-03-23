package com.example.shoppi.repository.productdetail

import com.example.shoppi.model.Product

class ProductDetailRepository(
    private val remoteDataSource: ProductDetailDataSource
) {

    suspend fun getProductDetail(productId: String): Product {
        return remoteDataSource.getProductDetail(productId)
    }
}