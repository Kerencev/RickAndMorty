package com.kerencev.rickandmorty.presentation.characters.filter.filterresult

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import com.kerencev.rickandmorty.domain.CharactersRepository
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.presentation.base.state.ListDataState
import com.kerencev.rickandmorty.presentation.base.viewmodel.BaseViewModel
import com.kerencev.rickandmorty.utils.subscribeByDefault

abstract class FilterCharactersResultViewModel(router: Router) : BaseViewModel(router) {

    abstract val liveData: LiveData<ListDataState<Character>>

    abstract fun getData(
        name: String = "",
        species: String = "",
        status: String = "",
        gender: String = ""
    )

    class Base(router: Router, private val repository: CharactersRepository) :
        FilterCharactersResultViewModel(router) {

        override val liveData = MutableLiveData<ListDataState<Character>>()

        override fun getData(name: String, species: String, status: String, gender: String) {
            liveData.value = ListDataState.Loading
            repository.getCharactersByFilter(name, species, status, gender)
                .subscribeByDefault()
                .subscribe(
                    {
                        liveData.value = ListDataState.Success(it)
                    },
                    {
                        liveData.value = ListDataState.Error
                        Log.d("FilterCharactersResultViewModel", it.toString())
                    }
                )
        }
    }
}