package com.mb.simpleapp.domain.contacts

import com.mb.simpleapp.models.ContactViewModel
import io.reactivex.Observable
import io.reactivex.Single

interface ContactsInteractor {
    fun syncAll(): Single<List<ContactViewModel>>
}