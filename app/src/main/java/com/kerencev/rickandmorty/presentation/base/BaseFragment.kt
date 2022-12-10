package com.kerencev.rickandmorty.presentation.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.kerencev.rickandmorty.presentation.main.NavigationActivity
import com.kerencev.rickandmorty.presentation.main.NavigationTab

abstract class BaseFragment<T : ViewBinding>(
    private val tab: NavigationTab,
    private val inflateBinding: (
        inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean
    ) -> T
) : Fragment(), OnBackPressedListener {
    private var _binding: T? = null
    protected val binding: T
        get() = _binding!!
    protected var navigationActivity: NavigationActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationActivity = requireActivity() as? NavigationActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = inflateBinding.invoke(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        navigationActivity?.setCurrentNavigationTab(tab)
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun onDetach() {
        navigationActivity = null
        super.onDetach()
    }
}