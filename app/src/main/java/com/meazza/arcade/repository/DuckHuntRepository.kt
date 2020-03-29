package com.meazza.arcade.repository

import com.meazza.arcade.model.Score
import com.meazza.arcade.model.Player
import com.meazza.arcade.network.DuckHuntDB
import com.meazza.arcade.network.TicTacToeDB

class DuckHuntRepository(private val database: DuckHuntDB) {


    suspend fun insertScore(score: Score) {
        database.insertScore(score)
    }

    suspend fun getDataRanking() = database.getDataRanking()
}