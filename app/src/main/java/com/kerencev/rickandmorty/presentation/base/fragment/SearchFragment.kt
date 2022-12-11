package com.kerencev.rickandmorty.presentation.base.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.kerencev.rickandmorty.presentation.main.NavigationActivity

abstract class SearchFragment<T : ViewBinding, V>(
    inflateBinding: (
        inflater: LayoutInflater, root: ViewGroup?, attachToRoot: Boolean
    ) -> T
) : ListDataFragment<T, V>(inflateBinding) {

    protected var navigationActivity: NavigationActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        navigationActivity = requireActivity() as? NavigationActivity
    }

    override fun onResume() {
        super.onResume()
        navigationActivity?.showBottomNavigation(false)
    }

    override fun onDetach() {
        navigationActivity = null
        super.onDetach()
    }
}