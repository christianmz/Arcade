package com.meazza.arcade.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.meazza.arcade.repository.AuthRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: AuthRepository) : ViewModel() {

    fun getGoogleAccount(intent: Intent) = repository.getGoogleAccount(intent)

    fun firebaseAuthWithGoogle(account: GoogleSignInAccount) {
        viewModelScope.launch {
            repository.firebaseAuthWithGoogle(account)
        }
    }
}