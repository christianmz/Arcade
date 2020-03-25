package com.meazza.arcade.ui.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.common.api.ApiException
import com.meazza.arcade.R
import com.meazza.arcade.network.AuthService
import com.meazza.arcade.network.GoogleClient
import com.meazza.arcade.ui.duck_hunt.DuckHuntActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var tokenID: String

    private val mainViewModel by inject<MainViewModel>()
    private val mGoogleSignClient by lazy { GoogleClient.signInClient(tokenID, this) }

    companion object {
        private const val RC_SIGN_IN = 101
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (AuthService.currentUser != null) {
            userLogged()
        }

        tokenID = getString(R.string.default_web_client_id)

        buttonAction()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = mainViewModel.getGoogleAccount(data!!)
            try {
                val account = task.getResult(ApiException::class.java)
                mainViewModel.firebaseAuthWithGoogle(account!!)
                userLogged()
            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }

    private fun buttonAction() {
        btn_sign_in.setOnClickListener {
            startActivityForResult(mGoogleSignClient.signInIntent, RC_SIGN_IN)
        }

        btn_tictactoe.setOnClickListener {

        }

        btn_duck_hunt.setOnClickListener {
            startActivity<DuckHuntActivity>()
        }

        btn_sign_out.setOnClickListener {
            signOut()
        }
    }

    private fun userLogged() {
        btn_tictactoe.visibility = View.VISIBLE
        btn_duck_hunt.visibility = View.VISIBLE
        btn_sign_out.visibility = View.VISIBLE
        btn_sign_in.visibility = View.GONE
    }

    private fun signOut() {
        AuthService.signOut
        btn_tictactoe.visibility = View.INVISIBLE
        btn_duck_hunt.visibility = View.INVISIBLE
        btn_sign_out.visibility = View.INVISIBLE
        btn_sign_in.visibility = View.VISIBLE
    }
}
