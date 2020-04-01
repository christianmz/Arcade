package com.meazza.arcade.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.meazza.arcade.R
import com.meazza.arcade.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private val authViewModel by inject<AuthViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        buttonAction()
    }

    private fun buttonAction(){
        tv_sign_in.setOnClickListener {
            startActivity<SignInActivity>()
        }

        btn_login.setOnClickListener {
            startActivity<MainActivity>()
        }
    }
}
