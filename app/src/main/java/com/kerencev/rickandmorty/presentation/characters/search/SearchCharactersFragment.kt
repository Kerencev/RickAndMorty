package com.kerencev.rickandmorty.presentation.characters.search

import android.os.Bundle
import android.util.Log
import android.view.View
import com.kerencev.rickandmorty.databinding.FragmentSearchCharactersBinding
import com.kerencev.rickandmorty.presentation.base.OnBackPressedListener
import com.kerencev.rickandmorty.presentation.base.ViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCharactersFragment :
    ViewBindingFragment<FragmentSearchCharactersBinding>(FragmentSearchCharactersBinding::inflate),
    OnBackPressedListener {

    private val viewModel: SearchCharactersViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.liveData.observe(viewLifecycleOwner) {
            Log.d("TAG", it.toString())
        }
        viewModel.getCharactersByName("rick")
    }

    override fun onBackPressed() = viewModel.onBackPressed()
}