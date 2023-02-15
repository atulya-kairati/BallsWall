package com.atulya.ballswall.ballsworld

import processing.core.PApplet
import processing.core.PConstants.ELLIPSE
import processing.core.PShape
import processing.core.PVector
import kotlin.random.Random


data class BallWrapper(
    private val creator: PApplet,
    val ball: PShape,
    val location: PVector,
    val velocity: PVector,
    val gravity: PVector,
    val dampeningFactor: Float
){



    companion object{

        fun getRandomFloat(start: Float, end: Float) = Random
            .nextDouble(start.toDouble(), end.toDouble()).toFloat()

        fun make(creator: PApplet): BallWrapper {
            val radius = getRandomFloat(50f, 100f)

            val R = getRandomFloat(0f, 255f)
            val G = getRandomFloat(0f, 255f)
            val B = getRandomFloat(0f, 255f)

            val aBall: PShape = creator.createShape(ELLIPSE, 0f, 0f, radius, radius)
            aBall.setFill(creator.color(R, G, B))
            aBall.setStroke(false)


            val location = PVector(
                getRandomFloat(0f, creator.width.toFloat()),
                getRandomFloat(0f, creator.height.toFloat()),
            )

            val velocity = PVector(
                getRandomFloat(0f, 5f),
                getRandomFloat(0f, 5f),
            )

            val gravity = PVector(0f, 0f)

            val dampening = getRandomFloat(0.2f, 1f)

            return BallWrapper(creator, aBall, location, velocity, gravity, dampening)
        }

    }

    fun checkDirections() {
        when {
            location.x > creator.width -> {
                location.x = creator.width.toFloat()
                velocity.x = velocity.x * -dampeningFactor
            }

            location.x < 0 -> {
                location.x = 0f
                velocity.x = velocity.x * -dampeningFactor
            }

            location.y > creator.height -> {
                location.y = creator.height.toFloat()
                velocity.y = velocity.y * -dampeningFactor
            }

            location.y < 0 -> {
                location.y = 0f
                velocity.y = velocity.y * -dampeningFactor
            }
        }
    }

    fun draw(){
        creator.shape(ball, location.x, location.y)
    }
}