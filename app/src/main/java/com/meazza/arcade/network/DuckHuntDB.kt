package com.meazza.arcade.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.meazza.arcade.model.Score
import kotlinx.coroutines.tasks.await

object DuckHuntDB {

    private val mDatabaseRef by lazy { FirebaseFirestore.getInstance() }

    private const val SCORES_REF = "scores"
    private const val POINTS_REF = "points"

    suspend fun insertScore(score: Score) {
        mDatabaseRef.collection(SCORES_REF).add(score).await()
    }

    suspend fun getDataRanking(): MutableList<Score> {

        val rankingList = ArrayList<Score>()

        val result = mDatabaseRef.collection(SCORES_REF)
            .orderBy(POINTS_REF, Query.Direction.DESCENDING)
            .limit(20)
            .get()
            .await()

        for (document in result) {
            val userItem = document.toObject(Score::class.java)
            rankingList.add(userItem)
        }

        return rankingList
    }
}