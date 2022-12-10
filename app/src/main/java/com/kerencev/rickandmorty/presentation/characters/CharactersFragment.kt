package com.kerencev.rickandmorty.presentation.characters

import android.os.Bundle
import android.util.Log
import android.view.View
import com.kerencev.rickandmorty.databinding.FragmentCharactersBinding
import com.kerencev.rickandmorty.presentation.base.BaseFragment
import com.kerencev.rickandmorty.presentation.main.NavigationTab
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment :
    BaseFragment<FragmentCharactersBinding>(
        NavigationTab.CHARACTERS,
        FragmentCharactersBinding::inflate
    ) {

    private val viewModel: CharactersViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeState()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun observeState() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            Log.d("TAG", it.toString())
        }
    }
}