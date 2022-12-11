package com.kerencev.rickandmorty.presentation.base

sealed class State<out T> {
    data class Success<T>(val data: List<T>) : State<T>()
    object Loading : State<Nothing>()
    object Error : State<Nothing>()
}
