package com.mb.simpleapp.injection

import com.mb.simpleapp.features.contacts.ContactsListView
import com.mb.simpleapp.injection.annotation.PerActivity
import dagger.Module
import dagger.Provides

@Module
class ContactsListPresenterModule(val view:ContactsListView) {
    @Provides @PerActivity fun view(): ContactsListView {
        return view
    }
}