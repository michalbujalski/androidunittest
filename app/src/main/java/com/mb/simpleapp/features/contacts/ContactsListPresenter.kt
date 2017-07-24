package com.mb.simpleapp.features.contacts

import android.util.Log
import com.mb.simpleapp.domain.contacts.ContactsInteractor
import javax.inject.Inject

class ContactsListPresenter @Inject constructor(){

    @Inject lateinit var contactInteractor: ContactsInteractor
    @Inject lateinit var view:ContactsListView

    fun fetchContacts(){
        contactInteractor.syncAll()
                .subscribe{
                    data->view.setData(data)
                }

    }
}