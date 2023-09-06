package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Chronometer

class MainActivity : AppCompatActivity() {

    lateinit var stopwatch: Chronometer
    lateinit var buttonStartStop: Button
    lateinit var buttonReset: Button

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
    }

    // to override an existing function just start typing it
    // can do this for the rest of the lifecycle functions (or any
    // function that exists in the superclass)
    override fun onStart() {
        super.onStart()
    }

    private fun wireWidgets() {
        stopwatch = findViewById(R.id.chronometer_main_stopwatch)
        buttonStartStop = findViewById(R.id.button_main_startStop)
        buttonReset = findViewById(R.id.button_main_reset)
    }
}