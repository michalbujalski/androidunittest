package com.mb.simpleapp.features.login

import com.mb.simpleapp.domain.UserInteractor


class LoginPresenterImpl(val userInteractor: UserInteractor, val view:LoginView):LoginPresenter {
    override fun initLogin(email: String, password: String) {
        if(!isValid(email,password)){return}
        view.showProgress()
        userInteractor
                .login(email,password)
                .subscribe(
                        {
                            view.hideProgress()
                            view.onLoginSuccess()
                        },
                        {err->
                            view.hideProgress()
                            view.onLoginError(err.message!!)
                        })
    }

    fun isValid(email:String, password: String):Boolean{
        val result = LoginFormValidator().validate(email,password)
        when(result){
            LoginFormValidator.EMAIL_EMPTY -> view.showEmailEmptyError()
            LoginFormValidator.PASSWORD_EMPTY -> view.showPasswordEmpty()
            LoginFormValidator.EMAIL_INCORRECT -> view.showImproperEmailFormat()
            LoginFormValidator.PASSWORD_TO_SHORT -> view.showPasswordToShort()
        }
        return result == LoginFormValidator.FORM_VALID
    }
}