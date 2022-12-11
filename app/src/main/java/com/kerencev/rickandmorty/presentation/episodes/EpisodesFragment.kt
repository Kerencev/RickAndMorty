package com.kerencev.rickandmorty.presentation.episodes

import com.kerencev.rickandmorty.databinding.FragmentEpisodesBinding
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.presentation.base.NavigationFragment
import com.kerencev.rickandmorty.presentation.base.OnBackPressedListener
import com.kerencev.rickandmorty.presentation.main.NavigationTab

class EpisodesFragment :
    NavigationFragment<FragmentEpisodesBinding, Character>(
        NavigationTab.EPISODES,
        FragmentEpisodesBinding::inflate
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