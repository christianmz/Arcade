package com.meazza.arcade.ui.tictactoe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.meazza.arcade.model.Player
import com.meazza.arcade.repository.TicTacToeRepository
import kotlinx.coroutines.launch

class TicTacToeViewModel(private val repository: TicTacToeRepository) : ViewModel() {

    fun createUser(player: Player) {
        viewModelScope.launch {
            repository.createPlayer(player)
        }
    }

    fun isFreeMatch() = liveData {
        emit(repository.isFreeMatch())
    }

    fun createMatch() = liveData {
        emit(repository.createMatch())
    }

    fun searchMatch() = liveData {
        emit(repository.searchMatch())
    }

    fun waitingPlayer() = liveData {
        emit(repository.waitingPlayer())
    }
}