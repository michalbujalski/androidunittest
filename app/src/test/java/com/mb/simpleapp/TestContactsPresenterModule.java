package com.mb.simpleapp;

import com.mb.simpleapp.domain.contacts.ContactsInteractor;
import com.mb.simpleapp.features.contacts.ContactsListView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module
public class TestContactsPresenterModule {
    @Singleton
    @Provides
    ContactsInteractor contactsInteractor(){
        return mock(ContactsInteractor.class);
    }
    @Singleton
    @Provides
    ContactsListView contactsListView(){
        return mock(ContactsListView.class);
    }
}
