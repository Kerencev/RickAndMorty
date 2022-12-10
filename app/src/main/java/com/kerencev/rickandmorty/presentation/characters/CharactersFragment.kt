package com.kerencev.rickandmorty.presentation.characters

import com.kerencev.rickandmorty.databinding.FragmentCharactersBinding
import com.kerencev.rickandmorty.presentation.base.BaseFragment
import com.kerencev.rickandmorty.presentation.main.NavigationTab

class CharactersFragment :
    BaseFragment<FragmentCharactersBinding>(
        NavigationTab.CHARACTERS,
        FragmentCharactersBinding::inflate
    ) {

    override fun onBackPressed() = viewModel.onBackPressed()
}