package com.meazza.arcade.ui.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.meazza.arcade.R
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val id = intent.getStringExtra("matchID")
        textView.text = id
    }
}
