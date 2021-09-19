package com.example.myapplication

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load


@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(photo: String?){
    this.load(photo)
}

@BindingAdapter("bindText")
fun TextView.bindText(text: String?){
    this.text = text
}


