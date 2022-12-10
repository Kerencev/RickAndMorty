package com.kerencev.rickandmorty.data.remote

import com.kerencev.rickandmorty.data.remote.model.characters.AllCharactersResponse
import com.kerencev.rickandmorty.domain.CharactersRepository
import com.kerencev.rickandmorty.domain.model.Character
import io.reactivex.rxjava3.core.Single

class CharactersRepositoryImpl(private val apiService: ApiService) : CharactersRepository {

    override fun getAllCharacters(): Single<List<Character>> {
        return apiService.getAllCharacters()
            .map { mapToListOfCharacters(it) }
    }

    private fun mapToListOfCharacters(allCharactersResponse: AllCharactersResponse): List<Character> {
        val result = mutableListOf<Character>()
        allCharactersResponse.results.forEach { characterResponse ->
            result.add(
                Character(
                    id = characterResponse.id,
                    name = characterResponse.name,
                    image = characterResponse.image,
                    species = characterResponse.species,
                    status = characterResponse.status,
                    gender = characterResponse.gender
                )
            )
        }
        return result
    }
}