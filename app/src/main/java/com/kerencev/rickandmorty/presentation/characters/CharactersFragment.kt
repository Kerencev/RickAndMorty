package com.kerencev.rickandmorty.presentation.characters

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.kerencev.rickandmorty.databinding.FragmentCharactersBinding
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.presentation.base.BaseFragment
import com.kerencev.rickandmorty.presentation.main.NavigationTab
import com.kerencev.rickandmorty.utils.makeGone
import com.kerencev.rickandmorty.utils.makeVisible
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharactersFragment :
    BaseFragment<FragmentCharactersBinding>(
        NavigationTab.CHARACTERS,
        FragmentCharactersBinding::inflate
    ) {

    private val viewModel: CharactersViewModel by viewModel()
    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
        observeState()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun initFields() {
        binding.charactersRv.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(
            binding.charactersRv.context,
            1
        )
        binding.charactersRv.addItemDecoration(dividerItemDecoration)
    }

    private fun observeState() {
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            renderData(state)
        }
    }

    private fun renderData(state: State) {
        when (state) {
            is State.Success -> {
                showSuccess(state.data)
            }
            is State.Loading -> {
                showLoading()
            }
            is State.Error -> {
                showError()
            }
        }
    }

    private fun showSuccess(data: List<Character>) = with(binding) {
        charactersRv.makeVisible()
        charactersLinearError.makeGone()
        charactersProgress.makeGone()
        adapter.submitList(data)
    }

    private fun showLoading() = with(binding) {
        charactersProgress.makeVisible()
        charactersRv.makeGone()
        charactersLinearError.makeGone()
    }

    private fun showError() = with(binding) {
        charactersLinearError.makeVisible()
        charactersProgress.makeGone()
        charactersRv.makeGone()
        charactersBtnReload.setOnClickListener {
            viewModel.getAllCharacters()
        }
    }
}