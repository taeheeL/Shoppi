package com.example.shoppi.ui.common

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.shoppi.AssetLoader
import com.example.shoppi.repository.HomeAssetDataSource
import com.example.shoppi.repository.HomeRepository
import com.example.shoppi.ui.home.HomeViewModel

class ViewModelFactory(private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){
            // TODO : DI 사용
            val repository = HomeRepository(HomeAssetDataSource(AssetLoader(context)))
            return HomeViewModel(repository) as T
        } else {
            throw java.lang.IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}