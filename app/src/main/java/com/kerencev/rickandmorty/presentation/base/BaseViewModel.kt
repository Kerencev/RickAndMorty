package com.kerencev.rickandmorty.presentation.base

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router

abstract class BaseViewModel(private val router: Router) : ViewModel() {
    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }
}