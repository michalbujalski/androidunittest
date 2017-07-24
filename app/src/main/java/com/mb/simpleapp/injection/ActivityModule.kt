package com.mb.simpleapp.injection

import android.app.Activity
import android.content.Context
import com.mb.simpleapp.injection.annotation.ContextActivity
import com.mb.simpleapp.injection.annotation.PerApp
import dagger.Module
import dagger.Provides


@Module
class ActivityModule (val activity:Activity){
    @Provides @PerApp fun activity():Activity = activity
    @Provides @PerApp @ContextActivity fun context(): Context = activity
}