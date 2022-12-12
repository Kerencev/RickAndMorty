package com.kerencev.rickandmorty.presentation.characters.filter.filterresult

import android.os.Bundle
import android.view.View
import com.kerencev.rickandmorty.databinding.FragmentFilterCharactersResultBinding
import com.kerencev.rickandmorty.domain.model.Character
import com.kerencev.rickandmorty.presentation.base.fragment.ListDataFragment
import com.kerencev.rickandmorty.presentation.base.fragment.OnBackPressedListener
import com.kerencev.rickandmorty.presentation.characters.search.SearchCharacterAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilterCharactersResultFragment :
    ListDataFragment<FragmentFilterCharactersResultBinding, Character>(
        FragmentFilterCharactersResultBinding::inflate
    ), OnBackPressedListener {

    private val viewModel: FilterCharactersResultViewModel by viewModel()
    private val adapter: SearchCharacterAdapter by lazy { SearchCharacterAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initFields()
        observeData()
        if (savedInstanceState == null) {
            initResponse()
        }
    }

    override fun showSuccess(data: List<Character>) {
        adapter.submitList(data)
    }

    override fun showLoading() {

    }

    override fun showError() {

    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun initFields() = with(binding) {
        filterCharactersResultRv.adapter = adapter
    }

    private fun observeData() {
        viewModel.liveData.observe(viewLifecycleOwner) {
            renderData(it)
        }
    }

    private fun initResponse() {
        val name = arguments?.getString(KEY_NAME)
        val species = arguments?.getString(KEY_SPECIES)
        val status = arguments?.getString(KEY_STATUS)
        val gender = arguments?.getString(KEY_GENDER)
        if (name != null && species != null && status != null && gender != null) {
            viewModel.getData(name, species, status, gender)
        }
    }

    companion object {

        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_SPECIES = "KEY_SPECIES"
        private const val KEY_STATUS = "KEY_STATUS"
        private const val KEY_GENDER = "KEY_GENDER"

        fun newInstance(
            name: String = "",
            species: String = "",
            status: String = "",
            gender: String = ""
        ): FilterCharactersResultFragment {
            return FilterCharactersResultFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_NAME, name)
                    putString(KEY_SPECIES, species)
                    putString(KEY_STATUS, status)
                    putString(KEY_GENDER, gender)
                }
            }
        }
    }
}