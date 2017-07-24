package com.mb.simpleapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.mb.simpleapp.features.contacts.ContactsListPresenter
import com.mb.simpleapp.features.contacts.ContactsListView
import kotlinx.android.synthetic.main.activity_main.*
import com.mb.simpleapp.features.login.LoginActivity
import com.mb.simpleapp.injection.*
import com.mb.simpleapp.models.ContactViewModel

class MainActivity : AppCompatActivity(), ContactsListView {
    val appComponent: AppComponent by lazy{
        (application as SimpleApplication).appComponent
    }
    val activityComponent: ActivityComponent by lazy{
        appComponent.with(ActivityModule(this))
    }
    val contactsListPresenterComponent: ContactsListPresenterComponent by lazy{
        appComponent.with(ContactsListPresenterModule(this))
    }
    val presenter: ContactsListPresenter = ContactsListPresenter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLogin.setOnClickListener{
            LoginActivity.startActivity(this)
        }

        activityComponent.inject(this)

        contactsListPresenterComponent
                .inject(presenter)
    }

    override fun setData(data: List<ContactViewModel>) {

    }
}
