package com.kerencev.rickandmorty.presentation.main

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen

class MainViewModel(private val router: Router) : ViewModel() {

    private var currentScreen: FragmentScreen? = null

    fun navigateTo(screen: FragmentScreen) {
        if (currentScreen == screen) return
        router.navigateTo(screen)
        currentScreen = screen
    }

    fun onBackPressed() {
        router.exit()
    }
}