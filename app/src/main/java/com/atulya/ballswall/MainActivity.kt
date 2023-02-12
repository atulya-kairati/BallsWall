package com.atulya.ballswall

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.atulya.ballswall.sketches.BallSketch
import processing.android.CompatUtils
import processing.android.PFragment
import processing.core.PApplet

//  https://android.processing.org/
//  https://android.processing.org/tutorials/android_studio/index.html

class MainActivity : AppCompatActivity() {

    private lateinit var sketch: PApplet

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val frame = FrameLayout(this)

        frame.id = CompatUtils.getUniqueViewId()

        setContentView(
            frame,
            ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
            )
        )

        sketch = BallSketch(applicationContext)

        val pFragment = PFragment(sketch)

        pFragment.setView(frame, this)

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (this::sketch.isInitialized) {
            sketch.onRequestPermissionsResult(
                requestCode,
                permissions,
                grantResults
            )
        }
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (this::sketch.isInitialized) {
            sketch.onNewIntent(intent)
        }
    }
}