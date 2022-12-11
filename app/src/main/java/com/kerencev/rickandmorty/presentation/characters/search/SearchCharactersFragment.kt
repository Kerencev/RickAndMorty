package com.kerencev.rickandmorty.presentation.characters.search

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import com.kerencev.rickandmorty.R
import com.kerencev.rickandmorty.databinding.FragmentSearchCharactersBinding
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.presentation.base.BaseFragment
import com.kerencev.rickandmorty.presentation.base.ErrorInputState
import com.kerencev.rickandmorty.presentation.base.OnBackPressedListener
import com.kerencev.rickandmorty.utils.hideKeyboard
import com.kerencev.rickandmorty.utils.makeGone
import com.kerencev.rickandmorty.utils.makeVisible
import com.kerencev.rickandmorty.utils.showKeyBoard
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchCharactersFragment :
    BaseFragment<FragmentSearchCharactersBinding, Character>(FragmentSearchCharactersBinding::inflate),
    OnBackPressedListener {

    private val viewModel: SearchCharactersViewModel by viewModel()
    private val adapter: SearchCharacterAdapter by lazy { SearchCharacterAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
        observeData()
        setAllClicks()
    }

    override fun showSuccess(data: List<Character>) = with(binding) {
        textInput.clearFocus()
        textInput.hideKeyboard(requireContext())
        searchCharactersRv.makeVisible()
        searchCharactersLinearError.makeGone()
        searchCharactersProgress.makeGone()
        adapter.submitList(data)
    }

    override fun showLoading() = with(binding) {
        searchCharactersProgress.makeVisible()
        searchCharactersRv.makeGone()
        searchCharactersLinearError.makeGone()
    }

    override fun showError() = with(binding) {
        searchCharactersLinearError.makeVisible()
        searchCharactersRv.makeGone()
        searchCharactersProgress.makeGone()
        SearchCharactersBtnReload.setOnClickListener {
            viewModel.getCharactersByName(textInput.text.toString())
        }
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun initFields() = with(binding) {
        textInput.showKeyBoard(requireContext())
        searchCharactersRv.adapter = adapter
    }

    private fun observeData() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.inputLiveData.observe(viewLifecycleOwner) { errorInputState ->
            renderInputErrorMessage(errorInputState)
        }
    }

    private fun renderInputErrorMessage(errorInputState: ErrorInputState) = with(binding) {
        textInputLayout.error = when (errorInputState) {
            ErrorInputState.EMPTY_INPUT -> resources.getString(R.string.empty_input_character_error)
            ErrorInputState.ONLY_LATIN -> resources.getString(R.string.latin_error)
            ErrorInputState.HIDE_ERROR -> null
        }
    }

    private fun setAllClicks() = with(binding) {
        textInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.getCharactersByName(textInput.text.toString())
            }
            true
        }
        textInputLayout.setStartIconOnClickListener {
            viewModel.onBackPressed()
        }
        textInputLayout.setEndIconOnClickListener {
            textInput.text?.clear()
        }
    }
}