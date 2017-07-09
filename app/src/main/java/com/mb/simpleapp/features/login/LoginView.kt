package com.mb.simpleapp.features.login

interface LoginView{
    fun onLoginSuccess()
    fun onLoginError(message:String)
    fun showEmailEmptyError()
    fun showImproperEmailFormat()
    fun showPasswordEmpty()
    fun showPasswordToShort()
    fun showProgress()
    fun hideProgress()
}