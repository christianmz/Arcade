package com.meazza.arcade.di

import com.meazza.arcade.ui.DuckHuntViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val duckHuntModule = module {
    viewModel { DuckHuntViewModel() }
}