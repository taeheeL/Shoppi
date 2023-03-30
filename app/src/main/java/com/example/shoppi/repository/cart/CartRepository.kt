package com.example.shoppi.repository.cart

import com.example.shoppi.model.CartItem
import com.example.shoppi.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CartRepository(
    private val localDatasource: CartItemLocalDatasource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun addCartItem(product: Product){
        withContext(ioDispatcher){
            val cartItem = CartItem(
                productId = product.productId,
                label = product.label,
                price = product.price,
                brandName = product.brandName ?: "",
                thumbnailImageUrl = product.thumbnailImageUrl ?: "",
                type = product.type ?: "",
                amount = 1
            )
            localDatasource.addCartItem(cartItem)
        }
    }


    suspend fun getCartItems(): List<CartItem>{
        return withContext(ioDispatcher){
            localDatasource.getCartItems()
        }
    }
}