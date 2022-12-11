package com.kerencev.rickandmorty.presentation.base.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.kerencev.rickandmorty.presentation.base.state.ListDataState

abstract class ListDataFragment<T : ViewBinding, V>(
    inflateBinding: (
        inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean
    ) -> T
) : ViewBindingFragment<T>(inflateBinding) {

    protected fun renderData(state: ListDataState<V>) {
        when (state) {
            is ListDataState.Success -> {
                showSuccess(state.data)
            }
            is ListDataState.Loading -> {
                showLoading()
            }
            is ListDataState.Error -> {
                showError()
            }
        }
    }

    abstract fun showSuccess(data: List<V>)
    abstract fun showLoading()
    abstract fun showError()
}