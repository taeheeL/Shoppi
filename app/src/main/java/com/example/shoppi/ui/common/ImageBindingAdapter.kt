package com.example.shoppi.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.shoppi.GlideApp

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        GlideApp.with(view)
            .load(imageUrl)
            .into(view)
    }
}