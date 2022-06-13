package com.example.melanzano.viewmodels

import android.os.CountDownTimer
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class TimerViewModel : ViewModel() {
    var timeInSeconds = mutableStateOf(10)
    var userTime = timeInSeconds.value
    var timerStarted = mutableStateOf(false)
    var pauseTimerStarted = mutableStateOf(false)
    var isFinished = false
    private lateinit var timer : CountDownTimer
    private lateinit var pauseTimer : CountDownTimer

    var counter = mutableStateOf(4)

    //Countdown timer that takes the specified time in seconds and starts a timer ticking down in second
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
                startPauseCounter()
            }
        }.start()
    }

    fun reset(timerLength: Int){
        if (timerStarted.value) {
            timer.cancel()
            timerStarted.value = false
        }
        if (pauseTimerStarted.value) {
            pauseTimer.cancel()
            pauseTimerStarted.value = false
        }
        timeInSeconds.value = timerLength

    }

    fun startPauseCounter(){
        counter.value--
        pauseTimerStarted.value = true
        if (counter.value < 1){
            timeInSeconds.value = userTime

        } else {
            timeInSeconds.value = (userTime * 0.25).toInt()
        }
        val timerLength = timeInSeconds.value * 1000.toLong()
        pauseTimer = object : CountDownTimer(timerLength, 1000){
            override fun onTick(millisUntilFinished: Long){
                timeInSeconds.value--
            }

            override fun onFinish() {
                pauseTimerStarted.value = false
                if (counter.value < 1){
                    counter.value = 4
                    reset(userTime)
                } else {
                    resetTimerAfterPause()
                }


            }
        }.start()
    }


    fun resetTimerAfterPause(){
        timeInSeconds.value = userTime
        startTimeCounter()
    }
}