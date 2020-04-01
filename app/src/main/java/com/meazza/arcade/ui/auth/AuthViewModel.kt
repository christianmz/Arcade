package com.meazza.arcade.ui.auth

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.meazza.arcade.network.AuthService
import kotlinx.coroutines.launch

class AuthViewModel(private val authService: AuthService) : ViewModel(){

    fun getGoogleAccount(intent: Intent) = authService.getGoogleAccount(intent)

    fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        viewModelScope.launch {
            authService.firebaseAuthWithGoogle(account)
        }
    }
}