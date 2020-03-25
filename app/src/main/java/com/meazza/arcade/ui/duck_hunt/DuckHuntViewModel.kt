package com.meazza.arcade.ui.duck_hunt

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meazza.arcade.model.User
import com.meazza.arcade.repository.DatabaseRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


class DuckHuntViewModel(private val repository: DatabaseRepository) : ViewModel() {

    fun duckRandomness(
        imageView: ImageView, drawable: Int,
        screenWidth: Int, screenHeight: Int
    ) {
        viewModelScope.launch {
            val min = 0
            val maxX = screenWidth - imageView.width
            val maxY = screenHeight - imageView.height
            val randomX = Random.nextInt(((maxX - min) + 1) + min)
            val randomY = Random.nextInt(((maxY - min) + 1) + min)

            delay(500)
            imageView.setImageResource(drawable)
            imageView.x = randomX.toFloat()
            imageView.y = randomY.toFloat()
        }
    }

    fun insertRecord(user: User) {
        viewModelScope.launch {
            try {
                repository.insertRecord(user)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}