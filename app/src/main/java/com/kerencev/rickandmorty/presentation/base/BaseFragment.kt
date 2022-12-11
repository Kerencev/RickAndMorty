package com.kerencev.rickandmorty.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding, V>(
    private val inflateBinding: (
        inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean
    ) -> T
) : Fragment() {
    protected var _binding: T? = null
    protected val binding: T
        get() = _binding!!

    protected fun renderData(state: State<V>) {
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

    abstract fun showSuccess(data: List<V>)
    abstract fun showLoading()
    abstract fun showError()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateBinding.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}