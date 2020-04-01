package com.meazza.arcade.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.meazza.arcade.R
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        setToolbar()
    }

    private fun setToolbar() {
        setSupportActionBar(tb_sign_in)
        supportActionBar?.title = getString(R.string.sign_in)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
