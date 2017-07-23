package com.mb.simpleapp.domain.contacts

import com.mb.simpleapp.data.contacts.ContactDataStore
import com.mb.simpleapp.models.ContactViewModel
import io.reactivex.Observable
import io.reactivex.Single

class ContactsInteractorImpl(val contactDataStore: ContactDataStore):ContactsInteractor {
    override fun syncAll(): Observable<List<ContactViewModel>> {
        return Observable
                .fromIterable(contactDataStore.getAll())
                .map{
                    contactData ->
                    ContactViewModel(contactData.name,contactData.phoneNum,contactData.isFav)
                }
                .toList()
    }
}