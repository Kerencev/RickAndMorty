package com.kerencev.rickandmorty.data.remote.model.characters

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("url")
    val url: String
)