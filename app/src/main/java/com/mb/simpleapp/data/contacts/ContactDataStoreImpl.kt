package com.mb.simpleapp.data.contacts

import io.reactivex.Single

class ContactDataStoreImpl: ContactDataStore {
    override fun getAll(): List<ContactData> {
        return listOf(
                ContactData("Tom","555111222",false),
                ContactData("Jane","500111222",false),
                ContactData("Chris","500222333",false),
                ContactData("Jake","522333111",false),
                ContactData("James","500999222",false)
            )
        }
    }
}