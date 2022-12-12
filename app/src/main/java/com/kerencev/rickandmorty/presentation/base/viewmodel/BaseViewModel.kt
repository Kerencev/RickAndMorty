package com.kerencev.rickandmorty.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel(private val router: Router) : ViewModel() {

    protected val bag = CompositeDisposable()

    fun onBackPressed(): Boolean {
        router.exit()
        return true
    }

    fun navigateTo(screen: Screen) {
        router.navigateTo(screen)
    }

    override fun onCleared() {
        bag.dispose()
        super.onCleared()
    }
}