package com.meazza.arcade.ui.duck_hunt

import android.widget.ImageView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random


class DuckHuntViewModel : ViewModel() {

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

}