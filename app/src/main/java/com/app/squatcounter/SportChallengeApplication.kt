package com.app.squatcounter

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class SportChallengeApplication : Application() {
    public var ischatOpen =false
    override fun onCreate() {
        super.onCreate()

        instance = this
        FirebaseApp.initializeApp(this@SportChallengeApplication)
    }

    companion object {
        private lateinit var instance: SportChallengeApplication

        fun getInstance(): SportChallengeApplication = instance
    }
    //FirebaseApp.initializeApp(this@MainActivity)
}