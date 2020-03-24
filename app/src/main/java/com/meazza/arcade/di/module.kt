package com.meazza.arcade.di

import com.meazza.arcade.network.AuthService
import com.meazza.arcade.repository.AuthRepository
import com.meazza.arcade.ui.duck_hunt.DuckHuntViewModel
import com.meazza.arcade.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val duckHuntModule = module {
    viewModel { DuckHuntViewModel() }
}

val mainModule = module {
    single { AuthService }
    single { AuthRepository(get()) }
    viewModel { MainViewModel(get()) }
}