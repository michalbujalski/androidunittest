package com.mb.simpleapp.injection

import com.mb.simpleapp.features.contacts.ContactsListPresenter
import com.mb.simpleapp.injection.annotation.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = arrayOf(ContactsListPresenterModule::class))
interface ContactsListPresenterComponent {
    fun inject(presenter: ContactsListPresenter)
}