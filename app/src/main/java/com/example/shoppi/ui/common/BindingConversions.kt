package com.example.shoppi.ui.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.databinding.BindingConversion

@BindingConversion
fun convertToColorDrawable(color:String):Drawable{
    return ColorDrawable(Color.parseColor(color))
}