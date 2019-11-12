package com.amaz.dev.android.jobsaround.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import com.amaz.dev.android.jobsaround.R
import com.amaz.dev.android.jobsaround.helpers.Constants
import com.android.airbag.helpers.SharedPreferencesManager
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        startAnimation()

    }

    private fun startAnimation() {
        var animation = AnimationUtils.loadAnimation(this, R.anim.alpha)
        animation.reset()
        splashImgV.clearAnimation()
        splashImgV.startAnimation(animation)
        animation = AnimationUtils.loadAnimation(this, R.anim.translate)
        animation.reset()

        val thread = object : Thread() {
            override fun run() {
                try {
                    var waited = 0
                    while (waited < 3500) {
                        sleep(100)
                        waited += 100
                    }

                    MainActivity.start(this@SplashActivity)

                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    this@SplashActivity.finish()
                }
            }
        }
        thread.start()
    }
}
