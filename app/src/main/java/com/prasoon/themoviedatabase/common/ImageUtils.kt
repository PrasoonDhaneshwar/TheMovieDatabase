package com.prasoon.themoviedatabase.common

import android.graphics.drawable.Drawable
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.prasoon.themoviedatabase.R

object ImageUtils {

    fun ImageView.loadImage(uri: String?, centerCrop: Boolean, viewProgressBar: ProgressBar) {
        val options = RequestOptions()
            .error(R.mipmap.ic_launcher_round)
        if (centerCrop) {
            // fit aspect ratio of view
            //options.centerCrop()
            options.centerInside()
            // maintain aspect ratio
            //options.fitCenter()
        }
        // Start loading the image again
        viewProgressBar.isVisible = true
        Glide.with(this.context)
            .setDefaultRequestOptions(options)
            .load(uri)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    viewProgressBar.isVisible = false
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    viewProgressBar.isVisible = false
                    return false
                }

            })
            .into(this)
    }
}