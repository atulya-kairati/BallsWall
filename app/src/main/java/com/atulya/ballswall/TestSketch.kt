package com.atulya.ballswall

import com.atulya.ballswall.ballsworld.BallWrapper
import processing.core.PApplet

class TestSketch : PApplet() {

    val ballz = ArrayList<BallWrapper>()
    private lateinit var b: BallWrapper

    override fun settings() {
        size(displayWidth, displayHeight)
    }

    override fun setup() {
        for (a in 1..10){
            ballz.add(BallWrapper.make(this))
        }
    }

    override fun draw() {
        for(b in ballz){
            b.draw()
        }
    }
}