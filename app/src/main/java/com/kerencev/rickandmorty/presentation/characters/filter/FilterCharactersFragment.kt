package com.kerencev.rickandmorty.presentation.characters.filter

import android.os.Bundle
import android.view.View
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
        binding.filterCharactersBtnApply.setOnClickListener {
            val name = binding.filterCharactersTextInputName.text.toString()
            val species = binding.filterCharactersTextInputSpecies.text.toString()
            val status = when (binding.filterCharactersRadioGroupStatus.checkedRadioButtonId) {
                R.id.filterCharactersRadioBtnAlive -> {
                    "alive"
                }
                R.id.filterCharactersRadioBtnDead -> {
                    "dead"
                }
                R.id.filterCharactersRadioBtnUnknownStatus -> {
                    "unknown"
                }
                else -> ""
            }
            val gender = when (binding.filterCharactersRadioGroupGender.checkedRadioButtonId) {
                R.id.filterCharactersRadioBtnMale -> {
                    "male"
                }
                R.id.filterCharactersRadioBtnFemale -> {
                    "female"
                }
                R.id.filterCharactersRadioBtnGenderless -> {
                    "genderless"
                }
                R.id.filterCharactersRadioBtnUnknownGender -> {
                    "unknown"
                }
                else -> ""
            }
            viewModel.navigateToResultScreen(name, species, status, gender)
        }
    }

    override fun onBackPressed() = viewModel.onBackPressed()

    companion object {

    }
}