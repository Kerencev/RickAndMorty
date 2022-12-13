package com.kerencev.rickandmorty.presentation.characters

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import com.kerencev.rickandmorty.databinding.FragmentCharactersBinding
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.navigation.FilterCharactersScreen
import com.kerencev.rickandmorty.navigation.SearchCharactersScreen
import com.kerencev.rickandmorty.presentation.base.fragment.BottomTabFragment
import com.kerencev.rickandmorty.presentation.base.fragment.OnBackPressedListener
import com.kerencev.rickandmorty.presentation.main.NavigationTab
import com.kerencev.rickandmorty.utils.makeGone
import com.kerencev.rickandmorty.utils.makeVisible
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharactersFragment :
    BottomTabFragment<FragmentCharactersBinding, Character>(
        NavigationTab.CHARACTERS,
        FragmentCharactersBinding::inflate
    ), OnBackPressedListener {

    private val viewModel: CharactersViewModel by viewModel()
    private val adapter: CharactersAdapter by lazy {
        CharactersAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
        observeState()
        setAllClickListeners()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    override fun showSuccess(data: List<Character>) = with(binding) {
        charactersRv.makeVisible()
        charactersLinearError.makeGone()
        charactersShimmer.makeGone()
        charactersShimmer.stopShimmer()
        adapter.submitList(data)
    }

    override fun showLoading() = with(binding) {
        charactersShimmer.makeVisible()
        charactersShimmer.startShimmer()
        charactersRv.makeGone()
        charactersLinearError.makeGone()
    }

    override fun showError() = with(binding) {
        charactersLinearError.makeVisible()
        charactersShimmer.makeGone()
        charactersRv.makeGone()
        charactersBtnReload.setOnClickListener {
            viewModel.getAllCharacters()
        }
    }

    private fun initFields() {
        binding.charactersRv.adapter = adapter
        val dividerItemDecoration = DividerItemDecoration(
            binding.charactersRv.context,
            1
        )
        binding.charactersRv.addItemDecoration(dividerItemDecoration)
    }

    private fun setAllClickListeners() = with(binding) {
        charactersSwipeRefresh.setOnRefreshListener {
            viewModel.getAllCharacters()
        }
        charactersActionSearch.setOnClickListener {
            viewModel.navigateTo(SearchCharactersScreen)
        }
        charactersActionFilter.setOnClickListener {
            viewModel.navigateTo(FilterCharactersScreen)
        }
    }

    private fun observeState() {
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            binding.charactersSwipeRefresh.isRefreshing = false
            renderData(state)
        }
    }
}