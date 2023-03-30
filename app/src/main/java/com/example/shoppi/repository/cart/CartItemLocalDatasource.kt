package com.example.shoppi.repository.cart

import com.example.shoppi.database.CartItemDao
import com.example.shoppi.model.CartItem

class CartItemLocalDatasource(
    private val dao: CartItemDao
) : CartItemDataSource {

    override suspend fun addCartItem(cartItem: CartItem) {
        dao.insert(cartItem)
    }

    override suspend fun getCartItems(): List<CartItem> {
        return dao.load()

    }

}