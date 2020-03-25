package com.meazza.arcade.network

import android.content.Intent
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await

object AuthService {

    private val mAuth by lazy { FirebaseAuth.getInstance() }

    val currentUser = mAuth.currentUser
    val signOut = mAuth.signOut()

    fun getGoogleAccount(intent: Intent)
            : Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(intent)

    suspend fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(account.idToken, null)
        mAuth.signInWithCredential(credential).await()
    }
}