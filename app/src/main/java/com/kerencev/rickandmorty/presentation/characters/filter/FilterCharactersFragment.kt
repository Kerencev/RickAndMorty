package com.kerencev.rickandmorty.presentation.characters.filter

import android.os.Bundle
import android.view.View
import com.kerencev.rickandmorty.databinding.FragmentFilterCharactersBinding
import com.kerencev.rickandmorty.presentation.base.fragment.OnBackPressedListener
import com.kerencev.rickandmorty.presentation.base.fragment.ViewBindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterCharactersFragment :
    ViewBindingFragment<FragmentFilterCharactersBinding>(FragmentFilterCharactersBinding::inflate),
    OnBackPressedListener {

    private val viewModel: FilterCharacterViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.filterCharactersBtnApply.setOnClickListener {

        }
    }

    override fun onBackPressed() = viewModel.onBackPressed()
}