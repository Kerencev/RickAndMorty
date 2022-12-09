package com.kerencev.rickandmorty.presentation.locations

import com.kerencev.rickandmorty.databinding.FragmentLocationsBinding
import com.kerencev.rickandmorty.presentation.base.BaseFragment
import com.kerencev.rickandmorty.presentation.main.NavigationTab

class LocationsFragment :
    BaseFragment<FragmentLocationsBinding>(
        NavigationTab.LOCATIONS,
        FragmentLocationsBinding::inflate
    ) {
}