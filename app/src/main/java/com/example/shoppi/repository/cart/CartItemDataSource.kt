package com.example.shoppi.repository.cart

import com.example.shoppi.model.CartItem

interface CartItemDataSource {

    suspend fun addCartItem(cartItem: CartItem)

    suspend fun getCartItems(): List<CartItem>
}