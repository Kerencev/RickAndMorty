package com.kerencev.rickandmorty.presentation.main

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.kerencev.rickandmorty.navigation.CharactersScreen

class MainViewModel(private val router: Router) : ViewModel() {

    private var currentScreen: FragmentScreen = CharactersScreen

    fun replaceStartScreen() {
        router.replaceScreen(CharactersScreen)
    }

    fun navigateTo(screen: FragmentScreen) {
        if (currentScreen == screen) return
        currentScreen = screen
        router.navigateTo(screen)
    }

    fun onBackPressed() {
        router.exit()
    }
}