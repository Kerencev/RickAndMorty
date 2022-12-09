package com.kerencev.rickandmorty.presentation.main

interface NavigationActivity {
    fun setCurrentNavigationTab(tab: NavigationTab)
}

enum class NavigationTab {
    CHARACTERS, LOCATIONS, EPISODES
}