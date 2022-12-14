package com.kerencev.rickandmorty.data.remote

import com.kerencev.rickandmorty.data.remote.model.characters.AllCharactersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    fun getAllCharacters(): Single<AllCharactersResponse>

    @GET("character/")
    fun getCharactersByName(@Query("name") name: String): Single<AllCharactersResponse>
}