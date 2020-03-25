package com.meazza.arcade.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.meazza.arcade.model.User
import kotlinx.coroutines.tasks.await

object DatabaseConnection {

    private val mDatabase by lazy { FirebaseFirestore.getInstance() }

    private const val USERS_REF = "users"
    private const val POINTS_REF = "points"

    suspend fun insertRecord(user: User) {
        mDatabase.collection(USERS_REF).add(user).await()
    }

    suspend fun getDataRanking(): MutableList<User> {

        val rankingList = ArrayList<User>()

        val result = mDatabase.collection(USERS_REF)
            .orderBy(POINTS_REF, Query.Direction.DESCENDING)
            .limit(20)
            .get()
            .await()

        for (document in result) {
            val userItem = document.toObject(User::class.java)
            rankingList.add(userItem)
        }

        return rankingList
    }
}