package com.mb.simpleapp.features.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.mb.simpleapp.R
import com.mb.simpleapp.data.UserDataStore
import com.mb.simpleapp.data.UserDataStoreImpl
import com.mb.simpleapp.domain.UserInteractorImpl
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity:AppCompatActivity(),LoginView {

    private lateinit var presenter:LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        presenter = LoginPresenterImpl(UserInteractorImpl(UserDataStoreImpl()),this)
        submit.setOnClickListener({
            presenter.initLogin(email.text.toString(),password.text.toString())
        })
        email.setText("asdf@qwer.com")
        password.setText( "asdfqwer" )

        progress.visibility = View.GONE
    }

    override fun onLoginSuccess() {
        finish()
    }

    override fun onLoginError(message: String) {

    }

    override fun showEmailEmptyError() {
        email.error = getString(R.string.error_empty_field)
    }

    override fun showImproperEmailFormat() {
        email.error = getString(R.string.error_email_format_incorrect)
    }

    override fun showPasswordEmpty() {
        password.error = getString(R.string.error_empty_field)
    }

    override fun showPasswordToShort() {
        password.error = getString(R.string.error_password_to_short)
    }

    override fun showProgress() {
        email.isEnabled = false
        password.isEnabled = false
        submit.isEnabled = false
        progress.post{progress.visibility = View.VISIBLE}
    }

    override fun hideProgress() {
        email.isEnabled = true
        password.isEnabled = false
        submit.isEnabled = false
        progress.post{progress.visibility = View.GONE}
    }

    companion object {
        fun startActivity(ctx: Context){
            ctx.startActivity(Intent(ctx,LoginActivity::class.java))
        }
    }
}