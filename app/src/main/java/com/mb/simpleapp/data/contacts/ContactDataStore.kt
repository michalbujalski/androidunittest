package com.mb.simpleapp.data.contacts

import io.reactivex.Single

interface ContactDataStore {
    fun getAll(): List<ContactData>
}