package com.kerencev.rickandmorty.presentation.episodes

import com.kerencev.rickandmorty.databinding.FragmentEpisodesBinding
import com.kerencev.rickandmorty.presentation.base.NavigationFragment
import com.kerencev.rickandmorty.presentation.base.OnBackPressedListener
import com.kerencev.rickandmorty.presentation.main.NavigationTab

class EpisodesFragment :
    NavigationFragment<FragmentEpisodesBinding>(
        NavigationTab.EPISODES,
        FragmentEpisodesBinding::inflate
    ), OnBackPressedListener {
    override fun onBackPressed(): Boolean {
        return true
    }
}