package com.haroldcalayan.common.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.haroldcalayan.R
import com.squareup.picasso.Picasso

@BindingAdapter("app:image")
fun setImage(view: ImageView, img: String?) {
    Picasso.get()
        .load(img)
        .placeholder(R.drawable.placeholder_image)
        .error(R.drawable.placeholder_image)
        .centerCrop()
        .fit()
        .into(view)
}