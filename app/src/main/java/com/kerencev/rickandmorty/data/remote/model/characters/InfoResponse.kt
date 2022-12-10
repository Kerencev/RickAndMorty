package com.kerencev.rickandmorty.data.remote.model.characters

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InfoResponse(
    @Expose
    @SerializedName("count")
    val count: Int,
    @Expose
    @SerializedName("next")
    val next: String,
    @Expose
    @SerializedName("pages")
    val pages: Int,
    @Expose
    @SerializedName("prev")
    val prev: Any
)