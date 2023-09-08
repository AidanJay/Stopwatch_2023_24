package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    lateinit var stopwatch: Chronometer
    lateinit var buttonStartStop: Button
    lateinit var buttonReset: Button

    var isRunning = false
    var timeWhenStopped = 0L

    // declaring a classwide constant in java:
    // public static final double PI = 3.14
    // in kotlin, we use a companion object:
    companion object {
        // TAG is the default var name for labeling your log statements
        val TAG = "MainActivity"
        // just for github testing purposes
        val PHYSICISTS_PI = 3
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: this is a log")

        wireWidgets()

        buttonStartStop.setOnClickListener {
            if(!isRunning) {
                isRunning = true
                buttonStartStop.text = "Stop"
                stopwatch.setBase(SystemClock.elapsedRealtime() + timeWhenStopped)
                stopwatch.start()
            } else {
                isRunning = false
                buttonStartStop.text = "Start"
                timeWhenStopped = stopwatch.getBase() - SystemClock.elapsedRealtime()
                stopwatch.stop()
            }
        }

        buttonReset.setOnClickListener {
            stopwatch.setBase(SystemClock.elapsedRealtime())
            timeWhenStopped = 0L

            if(!isRunning) {
                //reset
            } else {
                //reset
                isRunning = false
                buttonStartStop.text = "Start"
                stopwatch.stop()
            }
        }
    }

    // to override an existing function just start typing it
    // can do this for the rest of the lifecycle functions (or any
    // function that exists in the superclass)
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: this is a log")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: this is a log")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: this is a log")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: this is a log")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: this is a log")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: this is a log")
    }

    private fun wireWidgets() {
        stopwatch = findViewById(R.id.chronometer_main_stopwatch)
        buttonStartStop = findViewById(R.id.button_main_startStop)
        buttonReset = findViewById(R.id.button_main_reset)
    }
}