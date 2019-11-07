package com.amaz.dev.android.jobsaround.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.amaz.dev.android.jobsaround.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        MainActivity.start(this)
    }
}
