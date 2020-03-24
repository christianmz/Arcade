package com.meazza.arcade.repository

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.meazza.arcade.network.AuthService

class AuthRepository(private val authService: AuthService) {

    fun getGoogleAccount(intent: Intent) = authService.getGoogleAccount(intent)

    suspend fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        authService.firebaseAuthWithGoogle(account)
    }
}