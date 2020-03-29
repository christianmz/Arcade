package com.meazza.arcade.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.meazza.arcade.model.Match
import com.meazza.arcade.model.Player
import kotlinx.coroutines.tasks.await

object TicTacToeDB {

    private val mDatabaseRef by lazy { FirebaseFirestore.getInstance() }

    private val userId = AuthService.user?.uid

    private lateinit var query: QuerySnapshot
    var listener: ListenerRegistration? = null

    private const val PLAYERS_REF = "players"
    private const val MATCHES_REF = "matches"
    private const val PLAYER_TWO_ID = "playerTwoId"

    suspend fun createPlayer(player: Player) {
        mDatabaseRef.collection(PLAYERS_REF).document(player.id).set(player).await()
    }

    suspend fun isFreeMatch(): Boolean {

        query = mDatabaseRef.collection(MATCHES_REF)
            .whereEqualTo(PLAYER_TWO_ID, "")
            .get()
            .await()

        return query.size() != 0
    }

    suspend fun createMatch(): String {
        val document = mDatabaseRef.collection(MATCHES_REF).add(Match(userId)).await()
        return document.id
    }

    suspend fun searchMatch(): String {

        val docMatch = query.documents[0]
        val matchId = docMatch.id
        val match = docMatch.toObject(Match::class.java)
        match?.playerTwoId = userId.toString()

        mDatabaseRef.collection(MATCHES_REF).document(matchId).set(match!!).await()

        return matchId
    }

    fun waitingPlayer(): Boolean {

        var result = false

        listener = mDatabaseRef.collection(MATCHES_REF).document()
            .addSnapshotListener { document, _ ->
                result = !document?.get(PLAYER_TWO_ID)?.equals("")!!
            }

        return result
    }
}