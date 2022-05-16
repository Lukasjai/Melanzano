package com.example.melanzano.viewmodels

import android.os.CountDownTimer
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    var timeInSeconds = mutableStateOf(1200)
    var timerStarted = mutableStateOf(false)
    var isFinished = false
    private lateinit var timer : CountDownTimer

    //Countdown timer that takes the specified time in seconds and starts a timer ticking down in seconds
    fun startTimeCounter() {
        timerStarted = mutableStateOf(true)
        val timerLength = timeInSeconds.value * 1000.toLong()
      timer =  object : CountDownTimer(timerLength, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timeInSeconds.value--
            }

            override fun onFinish() {
                timerStarted = mutableStateOf(false)
                isFinished = true
            }
        }.start()

    }

    fun reset(timerLength: Int){
        timer.cancel()
        timeInSeconds.value = timerLength
        timerStarted.value = false
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