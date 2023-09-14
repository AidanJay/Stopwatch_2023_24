package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    lateinit var stopwatch: Chronometer
    lateinit var recentLap: Chronometer
    lateinit var buttonStartStop: Button
    lateinit var buttonReset: Button

    var isRunning = false
    var displayTime = 0L
    var lapTime = ""

    // declaring a classwide constant in java:
    // public static final double PI = 3.14
    // in kotlin, we use a companion object:
    companion object {
        // TAG is the default var name for labeling your log statements
        val TAG = "MainActivity"
        // just for github testing purposes
        val PHYSICISTS_PI = 3

        //make constants for your key-value pairs
        val STATE_DISPLAY_TIME = "the display time"
        val STATE_LAP_TIME = "the lap time"
        val STATE_IS_RUNNING = "if the chronometer is running"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate: this is a log")

        wireWidgets()

        // restore saveInstanceState if it exists
        if (savedInstanceState != null) {
            displayTime = savedInstanceState.getLong(STATE_DISPLAY_TIME)
            isRunning = savedInstanceState.getBoolean(STATE_IS_RUNNING)
            lapTime = savedInstanceState.getString(STATE_LAP_TIME).toString()
            if(!isRunning) {
                stopwatch.base = displayTime + SystemClock.elapsedRealtime()
                buttonStartStop.text = "Start"
                buttonReset.text = "Reset"
                recentLap.text = lapTime
                stopwatch.stop()
            } else {
                stopwatch.base = displayTime - SystemClock.elapsedRealtime()
                buttonStartStop.text = "Stop"
                buttonReset.text = "Lap"
                recentLap.text = lapTime
                stopwatch.start()
            }
        }

        buttonStartStop.setOnClickListener {
            if(!isRunning) {
                isRunning = true
                buttonStartStop.text = "Stop"
                buttonReset.text = "Lap"
                stopwatch.base = SystemClock.elapsedRealtime() + displayTime
                stopwatch.start()
            } else {
                isRunning = false
                buttonStartStop.text = "Start"
                buttonReset.text = "Reset"
                displayTime = stopwatch.base - SystemClock.elapsedRealtime()
                stopwatch.stop()
            }
        }

        buttonReset.setOnClickListener {
            if(!isRunning) {
                stopwatch.base = SystemClock.elapsedRealtime()
                recentLap.base = SystemClock.elapsedRealtime()
                displayTime = 0L
                stopwatch.stop()
            } else {
                lapTime = stopwatch.text.toString()
                recentLap.text = lapTime
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

    // Use this to preserve state through orientation changes
    override fun onSaveInstanceState(outState: Bundle) {
        // figure out the time that is currently displayed on the screen
        // and save that in a key-value pair in the bundle
        if(isRunning) {
            displayTime = SystemClock.elapsedRealtime() + stopwatch.base
            lapTime = recentLap.text.toString()
            //solve for base:
            // base = elapsedTime - displayTime
        }
        // if it weren't running, you would have saved the displayTime in
        // the stop button's onClickListener

        // save key-value pairs to the bundle before the superclass call
        outState.putLong(STATE_DISPLAY_TIME, displayTime)
        outState.putBoolean(STATE_IS_RUNNING, isRunning)
        outState.putString(STATE_LAP_TIME, lapTime)
        super.onSaveInstanceState(outState)
    }

    private fun wireWidgets() {
        stopwatch = findViewById(R.id.chronometer_main_stopwatch)
        recentLap = findViewById(R.id.chronometer_main_recentLap)
        buttonStartStop = findViewById(R.id.button_main_startStop)
        buttonReset = findViewById(R.id.button_main_reset)
    }
}