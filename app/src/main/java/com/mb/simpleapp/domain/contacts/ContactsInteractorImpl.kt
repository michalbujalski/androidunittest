package com.mb.simpleapp.domain.contacts

import com.mb.simpleapp.data.contacts.ContactDataStore
import com.mb.simpleapp.models.ContactViewModel
import io.reactivex.Observable
import io.reactivex.Single

class ContactsInteractorImpl(val contactDataStore: ContactDataStore):ContactsInteractor {
    override fun syncAll(): Single<List<ContactViewModel>> {
        return Observable
                .fromIterable(contactDataStore.getAll())
                .map{
                    (name, phoneNum, isFav) ->
                    ContactViewModel(name, phoneNum, isFav)
                }
                .toList()
    }
}