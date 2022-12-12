package com.kerencev.rickandmorty.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.github.terrakok.cicerone.androidx.FragmentScreen
import com.kerencev.rickandmorty.presentation.characters.CharactersFragment
import com.kerencev.rickandmorty.presentation.characters.filter.FilterCharactersFragment
import com.kerencev.rickandmorty.presentation.characters.filter.filterresult.FilterCharactersResultFragment
import com.kerencev.rickandmorty.presentation.characters.search.SearchCharactersFragment
import com.kerencev.rickandmorty.presentation.episodes.EpisodesFragment
import com.kerencev.rickandmorty.presentation.locations.LocationsFragment

object CharactersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return CharactersFragment()
    }
}

object LocationsScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return LocationsFragment()
    }
}

object EpisodesScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return EpisodesFragment()
    }
}

object SearchCharactersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return SearchCharactersFragment()
    }
}

object FilterCharactersScreen : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return FilterCharactersFragment()
    }
}

class FilterCharactersResultScreen(
    private val name: String = "",
    private val species: String = "",
    private val status: String = "",
    private val gender: String = ""
) : FragmentScreen {
    override fun createFragment(factory: FragmentFactory): Fragment {
        return FilterCharactersResultFragment.newInstance(name, species, status, gender)
    }
}