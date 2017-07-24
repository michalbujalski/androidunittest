package com.mb.simpleapp.injection

import android.content.Context
import com.mb.simpleapp.SimpleApplication
import com.mb.simpleapp.injection.annotation.ContextApp
import com.mb.simpleapp.injection.annotation.PerApp
import dagger.Module
import dagger.Provides

@Module
class AppModule (val application:SimpleApplication){
    @Provides @PerApp fun app():SimpleApplication = application
    @Provides @PerApp @ContextApp fun context(): Context = application
}