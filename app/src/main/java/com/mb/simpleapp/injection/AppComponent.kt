package com.mb.simpleapp.injection

import android.provider.ContactsContract
import com.mb.simpleapp.injection.annotation.PerApp
import dagger.Component

@PerApp
@Component(modules = arrayOf(AppModule::class,InteractorModule::class,DataModule::class))
interface AppComponent {
    fun with(activityModule: ActivityModule):ActivityComponent
    fun with(contactsListPresenterModule: ContactsListPresenterModule):ContactsListPresenterComponent
}