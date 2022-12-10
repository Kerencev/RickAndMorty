package com.kerencev.rickandmorty.data.remote.model.characters

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AllCharactersResponse(
    @Expose
    @SerializedName("info")
    val info: InfoResponse,
    @Expose
    @SerializedName("results")
    val results: List<CharacterResponse>
)