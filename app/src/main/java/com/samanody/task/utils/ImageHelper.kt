package com.samanody.task.utils

import android.widget.ImageView
import coil.load

object ImageHelper {
    fun load(imageView: ImageView, url: String) {
        imageView.load(url) {
            crossfade(true)
            placeholder(android.R.drawable.ic_menu_gallery)
        }
    }
}