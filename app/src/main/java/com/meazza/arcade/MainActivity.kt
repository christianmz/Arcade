package com.meazza.arcade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.meazza.arcade.ui.DuckHuntActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
}
