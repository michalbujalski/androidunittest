package com.mb.simpleapp.injection

import android.content.Context
import com.mb.simpleapp.data.contacts.ContactDataStore
import com.mb.simpleapp.data.contacts.ContactDataStoreImpl
import com.mb.simpleapp.injection.annotation.ContextApp
import com.mb.simpleapp.injection.annotation.PerApp
import dagger.Module
import dagger.Provides

@Module
class DataModule{
    @Provides @PerApp fun contactDataStore(@ContextApp ctx:Context): ContactDataStore{
        return ContactDataStoreImpl(ctx)
    }
}