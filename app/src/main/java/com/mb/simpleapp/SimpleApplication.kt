package com.mb.simpleapp

import android.app.Application
import com.mb.simpleapp.injection.AppComponent
import com.mb.simpleapp.injection.AppModule
import com.mb.simpleapp.injection.DaggerAppComponent

class SimpleApplication:Application() {
    val appComponent: AppComponent by lazy{
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}