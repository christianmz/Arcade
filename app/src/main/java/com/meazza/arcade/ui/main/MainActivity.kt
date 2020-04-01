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
import com.meazza.arcade.ui.tictactoe.TicTacToeActivity
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

        if (AuthService.user != null) {
            changeVisibility(true)
            btn_sign_out.text = AuthService.user.displayName
        }

        tokenID = getString(R.string.default_web_client_id)

        buttonAction()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = data?.let { mainViewModel.getGoogleAccount(it) }
            try {
                val account = task?.getResult(ApiException::class.java)
                mainViewModel.firebaseAuthWithGoogle(account!!)
                changeVisibility(true)
            } catch (e: ApiException) {
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }

    private fun buttonAction() {
        btn_google_sign_in.setOnClickListener {
            startActivityForResult(mGoogleSignClient.signInIntent, RC_SIGN_IN)
        }

        btn_tictactoe.setOnClickListener {
            startActivity<TicTacToeActivity>()
        }

        btn_duck_hunt.setOnClickListener {
            startActivity<DuckHuntActivity>()
        }

        btn_sign_out.setOnClickListener {
            AuthService.signOut
            changeVisibility(false)
        }
    }

    private fun changeVisibility(show: Boolean) {
        btn_tictactoe.visibility = if (show) View.VISIBLE else View.GONE
        btn_duck_hunt.visibility = if (show) View.VISIBLE else View.GONE
        btn_sign_out.visibility = if (show) View.VISIBLE else View.GONE
        btn_google_sign_in.visibility = if (show) View.GONE else View.VISIBLE
    }
}
