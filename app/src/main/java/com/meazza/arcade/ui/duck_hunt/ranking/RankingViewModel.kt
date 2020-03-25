package com.meazza.arcade.ui.duck_hunt.ranking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.meazza.arcade.repository.DatabaseRepository

class RankingViewModel(private val repository: DatabaseRepository) : ViewModel() {

    fun getDataRanking() = liveData {
        emit(repository.getDataRanking())
    }
}