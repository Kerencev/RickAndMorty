package com.kerencev.rickandmorty.presentation.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.kerencev.rickandmorty.domain.CharactersRepository
import com.kerencev.rickandmorty.utils.disposeBy
import com.kerencev.rickandmorty.utils.subscribeByDefault
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class CharactersViewModel : ViewModel() {

    abstract val liveData: LiveData<State>
    protected val bag = CompositeDisposable()

    abstract fun getAllCharacters()
    abstract fun onBackPressed(): Boolean

    override fun onCleared() {
        bag.dispose()
        super.onCleared()
    }

    class Base(private val router: Router, private val repository: CharactersRepository) :
        CharactersViewModel() {

        override val liveData = MutableLiveData<State>()

        init {
            getAllCharacters()
        }

        override fun getAllCharacters() {
            liveData.value = State.Loading
            repository.getAllCharacters()
                .subscribeByDefault()
                .subscribe(
                    {
                        liveData.value = State.Success(data = it)
                    },
                    {
                        liveData.value = State.Error
                        Log.d("CharactersViewModel", it.toString())
                    }
                ).disposeBy(bag)
        }

        override fun onBackPressed(): Boolean {
            router.exit()
            return true
        }

    }
}