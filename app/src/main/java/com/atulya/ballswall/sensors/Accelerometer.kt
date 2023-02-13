package com.atulya.ballswall.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

class Accelerometer(context: Context, listener: Listener) {

    interface Listener {
        fun onAccelerated(dx: Float, dy: Float, dz: Float)
    }

    private val sensorManager: SensorManager
    private val sensor: Sensor
    private val sensorEventListener: SensorEventListener

    init {
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorEventListener = object : SensorEventListener {
            override fun onSensorChanged(sensorEvent: SensorEvent?) {
                if (sensorEvent != null) {
                    listener.onAccelerated(
                        dx = sensorEvent.values[0],
                        dy = sensorEvent.values[1],
                        dz = sensorEvent.values[2],
                    )
                }
            }

            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
                Log.d(this.javaClass.toString(), "onAccuracyChanged: ")
            }

        }
    }

    fun register(){
        sensorManager.registerListener(sensorEventListener, sensor, SensorManager.SENSOR_DELAY_GAME)
    }

    fun unregister() {
        sensorManager.unregisterListener(sensorEventListener)
    }

}