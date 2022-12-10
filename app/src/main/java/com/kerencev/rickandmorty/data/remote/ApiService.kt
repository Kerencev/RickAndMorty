package com.kerencev.rickandmorty.data.remote

import com.kerencev.rickandmorty.data.remote.model.characters.AllCharactersResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ApiService {

    @GET("character")
    fun getAllCharacters(): Single<AllCharactersResponse>
}