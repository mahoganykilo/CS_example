package com.ap.csexample

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

/**
 * Main application for the CS Example app.
 */
@HiltAndroidApp
class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Plant Timber tree.
        if (BuildConfig.DEBUG) {
            Timber.plant()
        }
    }
}