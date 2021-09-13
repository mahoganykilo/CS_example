package com.ap.csexample

import com.ap.csexample.di.ApplicationComponent
import com.ap.csexample.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

/**
 * Main application for the CS Example app.
 */
class MainApplication : DaggerApplication() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        applicationComponent = DaggerApplicationComponent.factory().create(applicationContext)
        return applicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        // Plant Timber tree.
        if (BuildConfig.DEBUG) {
            Timber.plant()
        }
    }
}