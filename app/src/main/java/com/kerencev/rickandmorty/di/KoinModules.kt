package com.kerencev.rickandmorty.di

import com.github.terrakok.cicerone.Cicerone
import com.kerencev.rickandmorty.BuildConfig
import com.kerencev.rickandmorty.data.remote.ApiService
import com.kerencev.rickandmorty.data.remote.CharactersRepositoryImpl
import com.kerencev.rickandmorty.domain.CharactersRepository
import com.kerencev.rickandmorty.presentation.characters.CharactersViewModel
import com.kerencev.rickandmorty.presentation.characters.search.SearchCharactersViewModel
import com.kerencev.rickandmorty.presentation.main.MainViewModel
import com.kerencev.rickandmorty.utils.SearchValidator
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val navigationModule = module {
    val cicerone = Cicerone.create()
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }
}

val mainModule = module {
    single { createApiService() }
    viewModel { MainViewModel(router = get()) }
}

val charactersModule = module {
    single<CharactersRepository> { CharactersRepositoryImpl(apiService = get()) }
    factory<SearchValidator> { SearchValidator.Base() }
    viewModel<CharactersViewModel> { CharactersViewModel.Base(router = get(), repository = get()) }
    viewModel<SearchCharactersViewModel> {
        SearchCharactersViewModel.Base(
            router = get(),
            charactersRepository = get(),
            searchValidator = get()
        )
    }
}

private fun createApiService(): ApiService = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(ApiService::class.java)