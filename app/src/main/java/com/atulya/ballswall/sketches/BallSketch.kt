package com.atulya.ballswall.sketches

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.atulya.ballswall.sensors.Accelerometer
import processing.core.PApplet
import processing.core.PVector

class BallSketch : PApplet() {

    private val TAG = "# BallSketch"
    private val dampening = 0.9f

    private lateinit var location: PVector
    private lateinit var velocity: PVector
    private lateinit var gravity: PVector

    @RequiresApi(Build.VERSION_CODES.R)
    override fun settings() {
        size(displayWidth, displayHeight)
    }

    override fun setup() {

        location = PVector(displayWidth / 2.toFloat(), displayWidth / 1.5.toFloat())
        velocity = PVector(0f, 0f)
        gravity = PVector(0f, 0f)

        val listener = object : Accelerometer.Listener {
            override fun onAccelerated(dx: Float, dy: Float, dz: Float) {
                gravity.set(-dx / 10, dy / 10)
            }

        }

        val accelerometer = Accelerometer(context, listener)
        accelerometer.register()
    }

    override fun draw() {
        background(0)
        location.add(velocity)
        velocity.add(gravity)

        when {
            location.x > width -> {
                location.x = width.toFloat()
                velocity.x = velocity.x * -dampening
            }

            location.x < 0 -> {
                location.x = 0f
                velocity.x = velocity.x * -dampening
            }

            location.y > height -> {
                location.y = height.toFloat()
                velocity.y = velocity.y * -dampening
            }

            location.y < 0 -> {
                location.y = 0f
                velocity.y = velocity.y * -dampening
            }
        }

        Log.d(TAG, "draw: $location")

        // Display circle at location vector
        stroke(150f, 0f, 0f)
        strokeWeight(2f)
        fill(255f, 0f, 0f)
        ellipse(location.x, location.y, 48f, 48f)
    }
}