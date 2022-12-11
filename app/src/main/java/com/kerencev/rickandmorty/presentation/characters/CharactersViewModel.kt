package com.kerencev.rickandmorty.presentation.characters

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import com.kerencev.rickandmorty.domain.CharactersRepository
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.presentation.base.BaseViewModel
import com.kerencev.rickandmorty.presentation.base.State
import com.kerencev.rickandmorty.utils.disposeBy
import com.kerencev.rickandmorty.utils.subscribeByDefault

abstract class CharactersViewModel(router: Router) : BaseViewModel<Character>(router) {

    abstract fun getAllCharacters()

    class Base(router: Router, private val repository: CharactersRepository) :
        CharactersViewModel(router) {

        override val liveData = MutableLiveData<State<Character>>()

        init {
            getAllCharacters()
        }

        override fun getAllCharacters() {
            liveData.value = State.Loading
            repository.getAllCharacters()
                .subscribeByDefault()
                .subscribe(
                    {
                        liveData.value = State.Success(data = it)
                    },
                    {
                        liveData.value = State.Error
                        Log.d("CharactersViewModel", it.toString())
                    }
                ).disposeBy(bag)
        }
    }
}