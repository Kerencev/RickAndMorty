package com.kerencev.rickandmorty.presentation.base.state

sealed class ListDataState<out T> {
    data class Success<T>(val data: List<T>) : ListDataState<T>()
    object Loading : ListDataState<Nothing>()
    object Error : ListDataState<Nothing>()
}
