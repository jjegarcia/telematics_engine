package com.example.telematics_engine

import android.os.CountDownTimer
import android.util.Log

private const val RELOAD = 30

class TimerHandler constructor(
) {
    lateinit var timer: CountDownTimer
    var countDownCounter: Int = RELOAD
    fun setUp(future: Long, interval: Long) {
        timer = object : CountDownTimer(future, interval) {
            override fun onTick(millisUntilFinished: Long) {
                Log.i("Test", "msToFinish=$millisUntilFinished")
                serverTick()
            }

            override fun onFinish() {
                Log.i("Test", "Timer Finished")
                timer.start()
            }
        }
    }

    private fun serverTick() {
        countDownCounter--
        if (countDownCounter == 0) {
            Log.i("Timer","time out")
//        TODO("Not yet implemented")
            countDownCounter = RELOAD
        }
    }
}