package com.kerencev.rickandmorty.presentation.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.kerencev.rickandmorty.presentation.main.NavigationActivity
import com.kerencev.rickandmorty.presentation.main.NavigationTab

abstract class NavigationFragment<T : ViewBinding, V>(
    private val tab: NavigationTab,
    inflateBinding: (
        inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean
    ) -> T
) : BaseFragment<T, V>(inflateBinding) {

    protected var navigationActivity: NavigationActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationActivity = requireActivity() as? NavigationActivity
    }

    override fun onResume() {
        super.onResume()
        navigationActivity?.setCurrentNavigationTab(tab)
        navigationActivity?.showBottomNavigation(true)
    }

    override fun onDetach() {
        navigationActivity = null
        super.onDetach()
    }
}