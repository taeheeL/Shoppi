package com.example.shoppi.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppi.AssetLoader
import com.example.shoppi.network.ApiClient
import com.example.shoppi.repository.category.CategoryRemoteDataSource
import com.example.shoppi.repository.category.CategoryRepository
import com.example.shoppi.repository.categorydetail.CategoryDetailRemoteDataSource
import com.example.shoppi.repository.categorydetail.CategoryDetailRepository
import com.example.shoppi.repository.categorydetail.CategoryDetailViewModel
import com.example.shoppi.repository.home.HomeAssetDataSource
import com.example.shoppi.repository.home.HomeRepository
import com.example.shoppi.ui.category.CategoryViewModel
import com.example.shoppi.ui.home.HomeViewModel

class ViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                // TODO : DI 사용
                val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryViewModel::class.java) -> {
                val repository = CategoryRepository(CategoryRemoteDataSource(ApiClient.create()))
                CategoryViewModel(repository) as T
            }
            modelClass.isAssignableFrom(CategoryDetailViewModel::class.java) -> {
                val repository =
                    CategoryDetailRepository(CategoryDetailRemoteDataSource(ApiClient.create()))
                CategoryDetailViewModel(repository) as T
            }
            else -> {
                throw java.lang.IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
            }
        }
    }
}