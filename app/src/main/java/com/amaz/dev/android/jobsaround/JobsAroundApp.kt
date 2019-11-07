package com.amaz.dev.android.jobsaround

import android.app.Application
import com.amaz.dev.android.jobsaround.di.getModules
import com.amaz.dev.android.jobsaround.helpers.Constants
import com.amaz.dev.android.jobsaround.helpers.Utilities
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import sa.amaz.jaz.teacher.network.Network


class JobsAroundApp : Application() {

    override fun onCreate() {
        super.onCreate()


        Utilities.ovverideAppFont(applicationContext, "SERIF", "fonts/font_regular.ttf")


//        Network.init
        Network.init(Constants.BASE_URL, BuildConfig.DEBUG)

        startKoin {
            this@JobsAroundApp
            androidContext(this@JobsAroundApp)
            modules(*getModules())
        }
    }
}