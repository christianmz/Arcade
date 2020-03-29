package com.meazza.arcade.di

import com.meazza.arcade.network.AuthService
import com.meazza.arcade.network.DuckHuntDB
import com.meazza.arcade.network.TicTacToeDB
import com.meazza.arcade.repository.AuthRepository
import com.meazza.arcade.repository.DuckHuntRepository
import com.meazza.arcade.repository.TicTacToeRepository
import com.meazza.arcade.ui.duck_hunt.DuckHuntViewModel
import com.meazza.arcade.ui.duck_hunt.ranking.RankingViewModel
import com.meazza.arcade.ui.main.MainViewModel
import com.meazza.arcade.ui.tictactoe.TicTacToeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { AuthService }
    single { AuthRepository(get()) }
    viewModel { MainViewModel(get()) }
}

val ticTacToeModule = module {
    single { TicTacToeDB }
    single { TicTacToeRepository(get()) }
    viewModel { TicTacToeViewModel(get()) }
}

val duckHuntModule = module {
    single { DuckHuntDB }
    single { DuckHuntRepository(get()) }
    viewModel { DuckHuntViewModel(get()) }
    viewModel { RankingViewModel(get()) }
}
