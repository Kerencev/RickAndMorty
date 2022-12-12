package com.kerencev.rickandmorty.presentation.characters.filter

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.kerencev.rickandmorty.navigation.FilterCharactersResultScreen

abstract class FilterCharacterViewModel(private val router: Router) : ViewModel() {

    abstract fun navigateToResultScreen(
        name: String,
        species: String,
        status: String,
        gender: String
    )

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    class Base(
        private val router: Router
    ) : FilterCharacterViewModel(router) {

        override fun navigateToResultScreen(
            name: String,
            species: String,
            status: String,
            gender: String
        ) {
            router.navigateTo(FilterCharactersResultScreen(name, species, status, gender))
        }
    }
}