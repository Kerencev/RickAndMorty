package com.kerencev.rickandmorty.presentation.characters.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import com.kerencev.rickandmorty.domain.CharactersRepository
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.presentation.base.BaseViewModel
import com.kerencev.rickandmorty.presentation.base.State
import com.kerencev.rickandmorty.utils.subscribeByDefault

abstract class SearchCharactersViewModel(router: Router) : BaseViewModel<Character>(router) {

    abstract fun getCharactersByName(name: String)

    class Base(router: Router, private val charactersRepository: CharactersRepository) :
        SearchCharactersViewModel(router) {

        override val liveData = MutableLiveData<State<Character>>()

        override fun getCharactersByName(name: String) {
            liveData.value = State.Loading
            charactersRepository.getCharactersByName(name)
                .subscribeByDefault()
                .subscribe(
                    {
                        liveData.value = State.Success(it)
                    },
                    {
                        Log.d("SearchCharactersViewModel", it.toString())
                        liveData.value = State.Error
                    }
                )
        }
    }
}