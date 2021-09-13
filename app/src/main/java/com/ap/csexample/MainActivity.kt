package com.ap.csexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Application's main activity.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        MainApplication.applicationComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}