package com.meazza.arcade.repository

import com.meazza.arcade.model.User
import com.meazza.arcade.network.DatabaseConnection

class DatabaseRepository(private val database: DatabaseConnection) {

    suspend fun insertRecord(user: User) {
        database.insertRecord(user)
    }

    suspend fun getDataRanking() = database.getDataRanking()
}