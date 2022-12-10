package com.kerencev.rickandmorty.presentation.characters

import com.kerencev.rickandmorty.domain.model.Character

sealed class State {
    data class Success(val data: List<Character>) : State()
    object Loading : State()
    object Error : State()
}
