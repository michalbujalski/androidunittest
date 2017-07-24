package com.mb.simpleapp.features.contacts

import com.mb.simpleapp.models.ContactViewModel

interface ContactsListView {
    fun setData(data:List<ContactViewModel>)
}