package com.kerencev.rickandmorty.presentation.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.github.terrakok.cicerone.Router
import com.kerencev.rickandmorty.domain.CharactersRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class CharactersViewModel : ViewModel() {

    abstract val liveData: LiveData<State>

    abstract fun getAllCharacters()
    abstract fun onBackPressed(): Boolean

    class Base(private val router: Router, private val repository: CharactersRepository) :
        CharactersViewModel() {

        init {
            getAllCharacters()
        }

        override val liveData = MutableLiveData<State>()

        override fun getAllCharacters() {
            repository.getAllCharacters()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        liveData.postValue(State.Success(data = it))
                    },
                    {

                    }
                )
        }

        override fun onBackPressed(): Boolean {
            router.exit()
            return true
        }

    }
}