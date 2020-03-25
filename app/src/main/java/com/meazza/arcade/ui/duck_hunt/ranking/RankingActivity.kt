package com.meazza.arcade.ui.duck_hunt.ranking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.meazza.arcade.R
import kotlinx.android.synthetic.main.activity_ranking.*
import org.koin.android.ext.android.inject

class RankingActivity : AppCompatActivity() {

    private val rankingViewModel by inject<RankingViewModel>()
    private val mAdapter by lazy { RankingAdapter }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)

        setToolbar()
        setRecyclerView()
        observeData()
    }

    private fun setToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.ranking)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setRecyclerView() = with(rv_ranking) {
        setHasFixedSize(true)
        itemAnimator = DefaultItemAnimator()
        layoutManager = LinearLayoutManager(this@RankingActivity)
        adapter = mAdapter
    }

    private fun observeData() {
        rankingViewModel.getDataRanking().observe(this, Observer {
            mAdapter.setListData(it)
            mAdapter.notifyDataSetChanged()
        })
    }
}
