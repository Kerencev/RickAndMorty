package com.kerencev.rickandmorty.domain

import com.kerencev.rickandmorty.domain.model.Character
import io.reactivex.rxjava3.core.Single

interface CharactersRepository {
    fun getAllCharacters(): Single<List<Character>>
    fun getCharactersByName(name: String): Single<List<Character>>
}