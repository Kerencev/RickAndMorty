package com.kerencev.rickandmorty.presentation.locations

import com.kerencev.rickandmorty.databinding.FragmentLocationsBinding
import com.kerencev.rickandmorty.presentation.base.NavigationFragment
import com.kerencev.rickandmorty.presentation.base.OnBackPressedListener
import com.kerencev.rickandmorty.presentation.main.NavigationTab

class LocationsFragment :
    NavigationFragment<FragmentLocationsBinding>(
        NavigationTab.LOCATIONS,
        FragmentLocationsBinding::inflate
    ), OnBackPressedListener {
    override fun onBackPressed(): Boolean {
        return true
    }

}