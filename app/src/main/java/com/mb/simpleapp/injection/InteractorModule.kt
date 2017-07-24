package com.mb.simpleapp.injection

import com.mb.simpleapp.data.contacts.ContactDataStore
import com.mb.simpleapp.domain.contacts.ContactsInteractor
import com.mb.simpleapp.domain.contacts.ContactsInteractorImpl
import com.mb.simpleapp.injection.annotation.PerApp
import dagger.Module
import dagger.Provides

@Module
open class InteractorModule{
    @Provides @PerApp open fun contactsInteractor(dataStore:ContactDataStore): ContactsInteractor {
        return ContactsInteractorImpl(dataStore)
    }
}