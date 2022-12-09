package com.kerencev.rickandmorty.di

import com.github.terrakok.cicerone.Cicerone
import com.kerencev.rickandmorty.presentation.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val navigationModule = module {
    val cicerone = Cicerone.create()
    single { cicerone.router }
    single { cicerone.getNavigatorHolder() }
}

val mainModule = module {
    viewModel { MainViewModel(router = get()) }
}