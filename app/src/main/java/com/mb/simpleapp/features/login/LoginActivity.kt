package com.mb.simpleapp.features.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.mb.simpleapp.R
import com.mb.simpleapp.data.users.UserDataStoreImpl
import com.mb.simpleapp.domain.users.UserInteractorImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_login.*
import tea.Program

class LoginActivity:AppCompatActivity(),LoginView {
    override fun showSuccess() {
        Toast.makeText(this,"Log in succes",Toast.LENGTH_SHORT).show()
    }

    override fun setEmail(email: String) {
        this.email.setText(email)
    }

    override fun setPassword(password: String) {
        this.password.setText(password)
    }

    override fun setCanSubmit(canSubmit: Boolean) {
        this.submit.isEnabled = canSubmit
    }

    private lateinit var presenter:LoginPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val program = Program(AndroidSchedulers.mainThread())
        presenter = LoginPresenterImpl(UserInteractorImpl(UserDataStoreImpl()),this, program)
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
        Snackbar.make(submit, message, Snackbar.LENGTH_SHORT)
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
        password.isEnabled = true
        submit.isEnabled = true
        progress.post{progress.visibility = View.GONE}
    }

    companion object {
        fun startActivity(ctx: Context){
            ctx.startActivity(Intent(ctx,LoginActivity::class.java))
        }
    }
}