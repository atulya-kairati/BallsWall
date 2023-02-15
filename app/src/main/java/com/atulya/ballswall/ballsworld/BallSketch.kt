package com.atulya.ballswall.ballsworld

import android.os.Build
import androidx.annotation.RequiresApi
import com.atulya.ballswall.sensors.Accelerometer
import processing.core.PApplet
import processing.core.PVector

class BallSketch : PApplet() {

    private val TAG = "# BallSketch"

    val ballz = ArrayList<BallWrapper>()


    override fun settings() {
        size(displayWidth, displayHeight)
    }

    override fun setup() {

        for (a in 1..100){
            ballz.add(BallWrapper.make(this))
        }


        val listener = object : Accelerometer.Listener {
            override fun onAccelerated(dx: Float, dy: Float, dz: Float) {
                ballz.forEach{ ball->
                    ball.gravity.set(-dx / 10, dy / 10)
                }
            }
        }

        val accelerometer = Accelerometer(context, listener)
        accelerometer.register()
    }

    override fun draw() {
        background(0)

        ballz.forEach{ ball ->
            ball.apply {
                location.add(velocity)
                velocity.add(gravity)

                checkDirections()

                draw()
            }
        }

    }
}