package com.kerencev.rickandmorty.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.kerencev.rickandmorty.R

fun ImageView.loadCharacter(url: String) {
    Glide.with(this.context)
        .load(url)
        .circleCrop()
        .transition(DrawableTransitionOptions.withCrossFade(300))
        .placeholder(R.drawable.character_place_holder)
        .into(this)
}

