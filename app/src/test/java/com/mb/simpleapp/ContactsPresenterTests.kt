package com.mb.simpleapp

import com.mb.simpleapp.domain.contacts.ContactsInteractor
import com.mb.simpleapp.features.contacts.ContactsListPresenter
import com.mb.simpleapp.features.contacts.ContactsListView
import com.mb.simpleapp.models.ContactViewModel
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import javax.inject.Inject


class ContactsPresenterTests{
    @Inject lateinit var contactsInteractor:ContactsInteractor
    @Inject lateinit var view:ContactsListView

    private lateinit var presenter:ContactsListPresenter

    @Before fun setUp(){
        presenter = ContactsListPresenter()
        val componnent:TestContactsListComponent =
            DaggerTestContactsListComponent.builder().build();
        componnent.inject(presenter)
        componnent.inject(this)
    }


    @Test fun simpleTest(){
        whenever(contactsInteractor.syncAll()).thenReturn(Single.just(ArrayList<ContactViewModel>()))
        presenter.fetchContacts()
        Mockito.verify(view).setData(Mockito.anyList())
    }
}