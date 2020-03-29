package com.meazza.arcade.repository

import com.meazza.arcade.model.Player
import com.meazza.arcade.network.TicTacToeDB

class TicTacToeRepository(private val database: TicTacToeDB) {

    suspend fun createPlayer(player: Player) {
        database.createPlayer(player)
    }

    suspend fun isFreeMatch() = database.isFreeMatch()

    suspend fun createMatch() = database.createMatch()

    suspend fun searchMatch() = database.searchMatch()

    fun waitingPlayer() = database.waitingPlayer()
}