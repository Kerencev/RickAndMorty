package com.kerencev.rickandmorty.presentation.locations

import com.kerencev.rickandmorty.databinding.FragmentLocationsBinding
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.presentation.base.fragment.BottomTabFragment
import com.kerencev.rickandmorty.presentation.base.fragment.OnBackPressedListener
import com.kerencev.rickandmorty.presentation.main.NavigationTab

class LocationsFragment :
    BottomTabFragment<FragmentLocationsBinding, Character>(
        NavigationTab.LOCATIONS,
        FragmentLocationsBinding::inflate
    ), OnBackPressedListener {
    override fun onBackPressed(): Boolean {
        return true
    }

    override fun showSuccess(data: List<Character>) {
        TODO("Not yet implemented")
    }

    override fun showLoading() {
        TODO("Not yet implemented")
    }

    override fun showError() {
        TODO("Not yet implemented")
    }

}