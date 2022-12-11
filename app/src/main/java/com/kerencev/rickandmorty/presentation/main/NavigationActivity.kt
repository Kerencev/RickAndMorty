package com.kerencev.rickandmorty.presentation.main

interface NavigationActivity {
    fun setCurrentNavigationTab(tab: NavigationTab)
    fun showBottomNavigation(isShow: Boolean)
}

enum class NavigationTab {
    CHARACTERS, LOCATIONS, EPISODES
}