package com.kerencev.rickandmorty.presentation.episodes

import com.kerencev.rickandmorty.databinding.FragmentEpisodesBinding
import com.kerencev.rickandmorty.presentation.base.BaseFragment
import com.kerencev.rickandmorty.presentation.main.NavigationTab

class EpisodesFragment :
    BaseFragment<FragmentEpisodesBinding>(
        NavigationTab.EPISODES,
        FragmentEpisodesBinding::inflate
    ) {
    override fun onBackPressed() = viewModel.onBackPressed()
}