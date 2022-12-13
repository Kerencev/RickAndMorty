package com.kerencev.rickandmorty.presentation.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.github.terrakok.cicerone.Router
import com.kerencev.rickandmorty.domain.CharactersRepository
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.presentation.base.state.ListDataState
import com.kerencev.rickandmorty.presentation.base.viewmodel.BaseViewModel
import com.kerencev.rickandmorty.utils.disposeBy
import com.kerencev.rickandmorty.utils.subscribeByDefault

abstract class CharactersViewModel(router: Router) : BaseViewModel(router) {

    abstract val liveData: LiveData<ListDataState<Character>>
    abstract val filtersCountData: LiveData<Int>
    protected var nameFromFilter = ""
    protected var speciesFromFilter = ""
    protected var statusFromFilter = ""
    protected var genderFromFilter = ""

    abstract fun getCharacters(
        name: String = "",
        species: String = "",
        status: String = "",
        gender: String = "",
        isFiltered: Boolean = false
    )
    abstract fun getFilterName(): String
    abstract fun getFilterSpecies(): String
    abstract fun getFilterStatus(): String
    abstract fun getFilterGender(): String

    class Base(router: Router, private val repository: CharactersRepository) :
        CharactersViewModel(router) {

        override val liveData = MutableLiveData<ListDataState<Character>>()
        override val filtersCountData = MutableLiveData<Int>()

        init {
            getCharacters()
        }

        override fun getCharacters(
            name: String,
            species: String,
            status: String,
            gender: String,
            isFiltered: Boolean
        ) {
            liveData.value = ListDataState.Loading
            if (isFiltered) setLocalFilterCache(name, species, status, gender)
            repository.getCharactersByFilter(
                name = nameFromFilter,
                species = speciesFromFilter,
                status = statusFromFilter,
                gender = genderFromFilter
            )
                .subscribeByDefault()
                .subscribe(
                    {
                        liveData.value = ListDataState.Success(it)
                    },
                    {
                        liveData.value = ListDataState.Error
                        Log.d("FilterCharactersResultViewModel", it.toString())
                    }
                ).disposeBy(bag)
        }

        override fun getFilterName(): String {
            return nameFromFilter
        }

        override fun getFilterSpecies(): String {
            return speciesFromFilter
        }

        override fun getFilterStatus(): String {
            return statusFromFilter
        }

        override fun getFilterGender(): String {
            return genderFromFilter
        }

        private fun setLocalFilterCache(
            name: String,
            species: String,
            status: String,
            gender: String
        ) {
            var filtersCount = 0
            nameFromFilter = name
            if (name.isNotEmpty()) filtersCount++
            speciesFromFilter = species
            if (species.isNotEmpty()) filtersCount++
            statusFromFilter = status
            if (status.isNotEmpty()) filtersCount++
            genderFromFilter = gender
            if (gender.isNotEmpty()) filtersCount++
            filtersCountData.value = filtersCount
        }
    }
}