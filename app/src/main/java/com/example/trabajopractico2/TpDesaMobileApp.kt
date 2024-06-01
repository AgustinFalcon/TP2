package com.example.trabajopractico2

import android.app.Application


class TpDesaMobileApp : Application() {

    companion object {
        lateinit var instance: TpDesaMobileApp
    }


    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}