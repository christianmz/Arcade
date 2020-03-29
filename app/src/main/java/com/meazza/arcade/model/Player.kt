package com.meazza.arcade.model

import com.google.firebase.firestore.Exclude

data class Player(
    @get:Exclude
    var id: String = "",
    var playerName: String = "",
    var matches: Int = 0,
    var points: Int = 0
)