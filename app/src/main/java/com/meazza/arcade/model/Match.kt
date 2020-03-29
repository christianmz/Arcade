package com.meazza.arcade.model

import java.util.*

data class Match(
    val playerOneId: String? = "",
    var playerTwoId: String = "",
    val selectedCells: List<Int> = listOf(0, 0, 0, 0, 0, 0, 0, 0, 0),
    val isPlayerOneTurn: Boolean = true,
    val winnerId: String = "",
    val creationDate: Date = Date(),
    val playerLeavesMatchId: String = ""
)