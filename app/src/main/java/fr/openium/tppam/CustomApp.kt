package fr.openium.tppam

import android.app.Application
import timber.log.Timber

class CustomApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}