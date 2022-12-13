package com.kerencev.rickandmorty.presentation.characters.filter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.setFragmentResult
import com.kerencev.rickandmorty.R
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
        initFieldsFromArguments()
        setAllClicks()
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    private fun initFieldsFromArguments() = with(binding) {
        filterCharactersTextInputName.setText(arguments?.getString(KEY_NAME))
        filterCharactersTextInputSpecies.setText(arguments?.getString(KEY_SPECIES))
        when (arguments?.getString(KEY_STATUS)) {
            CHARACTER_ALIVE -> filterCharactersRadioBtnAlive.isChecked = true
            CHARACTER_DEAD -> filterCharactersRadioBtnDead.isChecked = true
            CHARACTER_UNKNOWN -> filterCharactersRadioBtnUnknownStatus.isChecked = true
            "" -> filterCharactersRadioGroupStatus.clearCheck()
        }
        when (arguments?.getString(KEY_GENDER)) {
            CHARACTER_MALE -> filterCharactersRadioBtnMale.isChecked = true
            CHARACTER_FEMALE -> filterCharactersRadioBtnFemale.isChecked = true
            CHARACTER_GENDERLESS -> filterCharactersRadioBtnGenderless.isChecked = true
            CHARACTER_UNKNOWN -> filterCharactersRadioBtnUnknownGender.isChecked = true
            "" -> filterCharactersRadioGroupGender.clearCheck()
        }
    }

    private fun setAllClicks() = with(binding) {
        filterCharactersToolbar.setNavigationOnClickListener { viewModel.onBackPressed() }
        filterCharactersToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.actionReset -> {
                    clearAllFilterFields()
                }
            }
            true
        }
        filterCharactersBtnApply.setOnClickListener {
            prepareData()
            viewModel.onBackPressed()
        }
        filterCharactersTextInputLayoutName.setEndIconOnClickListener {
            filterCharactersTextInputName.text?.clear()
        }
        filterCharactersTextInputLayoutSpecies.setEndIconOnClickListener {
            filterCharactersTextInputSpecies.text?.clear()
        }
    }

    private fun prepareData() = with(binding) {
        val name = filterCharactersTextInputName.text.toString()
        val species = filterCharactersTextInputSpecies.text.toString()
        val status = when (filterCharactersRadioGroupStatus.checkedRadioButtonId) {
            R.id.filterCharactersRadioBtnAlive -> CHARACTER_ALIVE
            R.id.filterCharactersRadioBtnDead -> CHARACTER_DEAD
            R.id.filterCharactersRadioBtnUnknownStatus -> CHARACTER_UNKNOWN
            else -> ""
        }
        val gender = when (filterCharactersRadioGroupGender.checkedRadioButtonId) {
            R.id.filterCharactersRadioBtnMale -> CHARACTER_MALE
            R.id.filterCharactersRadioBtnFemale -> CHARACTER_FEMALE
            R.id.filterCharactersRadioBtnGenderless -> CHARACTER_GENDERLESS
            R.id.filterCharactersRadioBtnUnknownGender -> CHARACTER_UNKNOWN
            else -> ""
        }

        setFragmentResult(FILTER_FRAGMENT_RESULT_KEY, Bundle().apply {
            putString(KEY_NAME, name)
            putString(KEY_SPECIES, species)
            putString(KEY_STATUS, status)
            putString(KEY_GENDER, gender)
        })
    }

    private fun clearAllFilterFields() = with(binding) {
        filterCharactersTextInputName.text = null
        filterCharactersTextInputSpecies.text = null
        filterCharactersRadioGroupStatus.clearCheck()
        filterCharactersRadioGroupGender.clearCheck()
    }

    companion object {
        const val FILTER_FRAGMENT_RESULT_KEY = "FILTER_FRAGMENT_RESULT_KEY"
        const val KEY_NAME = "KEY_NAME"
        const val KEY_SPECIES = "KEY_SPECIES"
        const val KEY_STATUS = "KEY_STATUS"
        const val KEY_GENDER = "KEY_GENDER"
        const val CHARACTER_ALIVE = "alive"
        const val CHARACTER_DEAD = "dead"
        const val CHARACTER_UNKNOWN = "unknown"
        const val CHARACTER_MALE = "male"
        const val CHARACTER_FEMALE = "female"
        const val CHARACTER_GENDERLESS = "genderless"

        fun newInstance(
            name: String,
            species: String,
            status: String,
            gender: String
        ): FilterCharactersFragment {
            return FilterCharactersFragment().apply {
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