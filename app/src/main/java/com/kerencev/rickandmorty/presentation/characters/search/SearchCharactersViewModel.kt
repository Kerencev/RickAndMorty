package com.kerencev.rickandmorty.presentation.characters.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import com.kerencev.rickandmorty.domain.CharactersRepository
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.presentation.base.BaseViewModel
import com.kerencev.rickandmorty.presentation.base.ErrorInputState
import com.kerencev.rickandmorty.presentation.base.State
import com.kerencev.rickandmorty.utils.SearchValidator
import com.kerencev.rickandmorty.utils.subscribeByDefault

abstract class SearchCharactersViewModel(router: Router) : BaseViewModel<Character>(router) {

    abstract val inputLiveData: LiveData<ErrorInputState>

    abstract fun getCharactersByName(name: String)

    class Base(
        router: Router,
        private val charactersRepository: CharactersRepository,
        private val searchValidator: SearchValidator
    ) :
        SearchCharactersViewModel(router) {

        override val liveData = MutableLiveData<State<Character>>()
        override val inputLiveData = MutableLiveData<ErrorInputState>()

        override fun getCharactersByName(name: String) {
            if (name.isEmpty()) {
                inputLiveData.value = ErrorInputState.EMPTY_INPUT
                return
            }
            if (!searchValidator.validateForCyrillic(name)) {
                inputLiveData.value = ErrorInputState.ONLY_LATIN
                return
            }

            liveData.value = State.Loading
            charactersRepository.getCharactersByName(name)
                .subscribeByDefault()
                .subscribe(
                    {
                        inputLiveData.value = ErrorInputState.HIDE_ERROR
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