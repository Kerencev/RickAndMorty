package com.kerencev.rickandmorty.data.remote.model.characters

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("image")
    val image: String,
    @Expose
    @SerializedName("species")
    val species: String,
    @Expose
    @SerializedName("status")
    val status: String,
    @Expose
    @SerializedName("gender")
    val gender: String,
    @Expose
    @SerializedName("created")
    val created: String,
    @Expose
    @SerializedName("episode")
    val episode: List<String>,
    @Expose
    @SerializedName("location")
    val location: LocationResponse,
    @Expose
    @SerializedName("origin")
    val origin: OriginResponse,
    @Expose
    @SerializedName("type")
    val type: String,
    @Expose
    @SerializedName("url")
    val url: String
)