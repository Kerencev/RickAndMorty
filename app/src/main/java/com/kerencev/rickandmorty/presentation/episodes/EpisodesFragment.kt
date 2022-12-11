package com.kerencev.rickandmorty.presentation.episodes

import com.kerencev.rickandmorty.databinding.FragmentEpisodesBinding
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.presentation.base.fragment.BottomTabFragment
import com.kerencev.rickandmorty.presentation.base.fragment.OnBackPressedListener
import com.kerencev.rickandmorty.presentation.main.NavigationTab

class EpisodesFragment :
    BottomTabFragment<FragmentEpisodesBinding, Character>(
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