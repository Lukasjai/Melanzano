package com.example.melanzano.viewmodels

import android.os.CountDownTimer
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TimerViewModel : ViewModel() {
    var timeInSeconds = mutableStateOf(10)
    var isFinished = false

    //Countdown timer that takes the specified time in seconds and starts a timer ticking down in seconds
    fun startTimeCounter() {
        val timerLength = timeInSeconds.value * 1000.toLong()
        object : CountDownTimer(timerLength, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeInSeconds.value--
            }

            override fun onFinish() {
                isFinished = true
            }
        }.start()
    }


/*    fun timer() = runBlocking {
        launch {
            val max = timeInSeconds.value;
                delay(1000L)
                timeInSeconds.value--
                updateTimer()
        }}

    fun updateTimer(){
        var minutes = (timeInSeconds.value/60).toInt()
        time.add(0,minutes)
        var seconds = timeInSeconds.value % 60
        time.add(1, seconds)
    }*/

}