package com.mb.simpleapp;

import com.mb.simpleapp.domain.contacts.ContactsInteractor;
import com.mb.simpleapp.features.contacts.ContactsListPresenter;
import com.mb.simpleapp.features.contacts.ContactsListView;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = TestContactsPresenterModule.class)
public interface TestContactsListComponent {
    void inject(ContactsPresenterTests testClass);
    void inject(ContactsListPresenter presenter);
}
