package com.mb.simpleapp.injection

import android.content.Context
import com.mb.simpleapp.MainActivity
import com.mb.simpleapp.SimpleApplication
import com.mb.simpleapp.injection.annotation.ContextApp
import com.mb.simpleapp.injection.annotation.PerActivity
import com.mb.simpleapp.injection.annotation.PerApp
import dagger.Component
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent{
    fun inject(mainActivity: MainActivity)
}