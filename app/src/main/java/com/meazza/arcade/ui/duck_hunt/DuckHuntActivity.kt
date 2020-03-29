package com.meazza.arcade.ui.duck_hunt

import android.graphics.Point
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.meazza.arcade.R
import com.meazza.arcade.model.Score
import com.meazza.arcade.network.AuthService
import com.meazza.arcade.ui.duck_hunt.ranking.RankingActivity
import com.meazza.arcade.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_duck_hunt.*
import org.jetbrains.anko.startActivity
import org.koin.android.ext.android.inject

class DuckHuntActivity : AppCompatActivity() {

    private val duckHuntViewModel by inject<DuckHuntViewModel>()

    private var screenWidth = 0
    private var screenHeight = 0
    private var counter = 0
    private var gameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_duck_hunt)

        initDisplay()
        startButton()
        huntedDucks()
    }

    private fun initDisplay() {
        val size = Point()
        windowManager.defaultDisplay.getSize(size)
        screenWidth = size.x
        screenHeight = size.y
    }

    private fun startButton() {
        btn_start_duck_hunt.setOnClickListener {

            counterDownTimer()
            viewVisibility()

            duckHuntViewModel.duckRandomness(
                iv_duck_flying,
                R.drawable.duck,
                screenWidth,
                screenHeight
            )
        }
    }

    private fun huntedDucks() {
        iv_duck_flying.setOnClickListener {
            iv_duck_flying.setImageResource(R.drawable.duck_clicked)
            tv_duck_points.text = "${counter++}"
            duckHuntViewModel.duckRandomness(
                iv_duck_flying,
                R.drawable.duck,
                screenWidth,
                screenHeight
            )
        }
    }

    private fun viewVisibility() {
        btn_start_duck_hunt.visibility = View.GONE
        iv_duck_hunt_logo.visibility = View.GONE
        iv_duck_flying.visibility = View.VISIBLE
    }

    private fun counterDownTimer() {
        object : CountDownTimer(60000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tv_duck_time.text = "${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                gameOver = true
                val userName = AuthService.user?.displayName!!
                val user = Score(userName, counter)
                duckHuntViewModel.insertRecord(user)
                showAlert()
            }
        }.start()
    }

    private fun showAlert() {
        AlertDialog.Builder(this).apply {
            setTitle(R.string.game_over)
            setMessage("Total Points: $counter")
            setPositiveButton(R.string.exit) { _, _ ->
                startActivity<MainActivity>()
                finish()
            }
            setNegativeButton(R.string.ranking) { _, _ ->
                startActivity<RankingActivity>()
                finish()
            }
            setCancelable(false)
        }.create().show()
    }
}
