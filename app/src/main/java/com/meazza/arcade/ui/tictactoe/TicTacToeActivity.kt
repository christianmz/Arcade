package com.meazza.arcade.ui.tictactoe

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.meazza.arcade.R
import com.meazza.arcade.model.Player
import com.meazza.arcade.network.AuthService
import com.meazza.arcade.network.TicTacToeDB
import com.meazza.arcade.ui.duck_hunt.DuckHuntActivity
import kotlinx.android.synthetic.main.activity_tic_tac_toe.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.longToast
import org.koin.android.ext.android.inject

class TicTacToeActivity : AppCompatActivity() {

    private val viewModel by inject<TicTacToeViewModel>()
    private val playerID by lazy { AuthService.user?.uid }
    private val playerName by lazy { AuthService.user?.displayName }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tic_tac_toe)

        if (AuthService.user != null) {
            viewModel.createUser(Player(playerID!!, playerName!!))
        }

        changeVisibility(true)

        btn_play.setOnClickListener {
            changeVisibility(false)
            startGame()
        }
    }

    private fun changeVisibility(show: Boolean) {
        btn_play.visibility = if (show) View.VISIBLE else View.GONE
        iv_tictactoe.visibility = if (show) View.VISIBLE else View.GONE
        btn_ranking_tictactoe.visibility = if (show) View.VISIBLE else View.GONE
        pb_loading.visibility = if (show) View.GONE else View.VISIBLE
        tv_loading.visibility = if (show) View.GONE else View.VISIBLE
    }

    private fun startGame() {

        var matchID: String? = null

        viewModel.isFreeMatch().observe(this, Observer {

            if (it) {
                longToast(R.string.searching_match)

                viewModel.searchMatch().observe(this, Observer { id ->
                    matchID = id
                })

                startActivity(intentFor<MatchActivity>("matchID" to matchID))

            } else {

                longToast(R.string.creating_match)

                viewModel.createMatch().observe(this, Observer { id ->

                    matchID = id

                    viewModel.waitingPlayer().observe(this, Observer { result ->

                        if (result) {
                            if (TicTacToeDB.listener != null) {
                                TicTacToeDB.listener!!.remove()
                            }
                            startActivity(intentFor<MatchActivity>("matchID" to matchID))
                        }
                    })
                })
            }
        })
    }
}
