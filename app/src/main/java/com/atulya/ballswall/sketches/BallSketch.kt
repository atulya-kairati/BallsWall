package com.atulya.ballswall.sketches

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.atulya.ballswall.sensors.Accelerometer
import processing.core.PApplet
import processing.core.PVector

class BallSketch(context: Context): PApplet() {

    private lateinit var location: PVector
    private lateinit var velocity: PVector
    private lateinit var gravity: PVector

    @RequiresApi(Build.VERSION_CODES.R)
    override fun settings(){
        size(displayWidth, displayHeight)
    }

    override fun setup() {

        val listener = object : Accelerometer.Listener {
            override fun onAccelerated(dx: Float, dy: Float, dz: Float) {
                Log.d(this.javaClass.toString(), "$dx, $dy, $dz")
            }

        }

        val accelerometer = Accelerometer(context, listener)
        accelerometer.register()

        location = PVector(displayWidth/2.toFloat(), displayWidth/1.5.toFloat())
        velocity = PVector(10.5f, 2.1f)
        gravity = PVector(0f, 0.98f)
    }

    override fun draw() {
        background(0)
        location.add(velocity)
        velocity.add(gravity)

        if ((location.x > width) || (location.x < 0)) {
            velocity.x = velocity.x * -1
        }

        if (location.y > height) {
            // We're reducing velocity ever so slightly
            // when it hits the bottom of the window
            velocity.y = velocity.y * -0.95f
            location.y = height.toFloat()
        }

        // Display circle at location vector
        stroke(150f, 0f, 0f)
        strokeWeight(2f)
        fill(255f, 0f, 0f)
        ellipse(location.x,location.y,48f,48f)
    }
}