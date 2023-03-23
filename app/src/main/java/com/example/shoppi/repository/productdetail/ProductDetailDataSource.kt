package com.example.shoppi.repository.productdetail

import com.example.shoppi.model.Product


interface ProductDetailDataSource {

    suspend fun getProductDetail(productId: String): Product

}