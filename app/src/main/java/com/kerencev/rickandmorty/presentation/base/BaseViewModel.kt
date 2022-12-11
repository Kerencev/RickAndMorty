package com.kerencev.rickandmorty.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel<T>(private val router: Router) : ViewModel() {

    abstract val liveData: LiveData<State<T>>
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