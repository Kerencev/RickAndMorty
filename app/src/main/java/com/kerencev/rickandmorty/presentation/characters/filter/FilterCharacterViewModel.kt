package com.kerencev.rickandmorty.presentation.characters.filter

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router

abstract class FilterCharacterViewModel(private val router: Router) : ViewModel() {

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    class Base(
        router: Router
    ) : FilterCharacterViewModel(router) {


    }
}