package com.example.myapplication.adapters

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.myapplication.R


@BindingAdapter("loadImageFromUrl")
fun ImageView.loadImageFromUrl(photo: String?){
    if (photo.isNullOrEmpty()) {
        this.load(R.drawable.ic_blank)
    } else{
        if (photo == "N/A"){
            this.load(R.drawable.ic_blank)
        } else {
            this.load(photo)
        }
    }
}

@BindingAdapter("bindText")
fun TextView.bindText(text: String?){
    this.text = text
}


