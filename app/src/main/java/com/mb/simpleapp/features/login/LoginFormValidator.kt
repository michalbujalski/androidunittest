package com.mb.simpleapp.features.login

class LoginFormValidator {
    private val emailRegex = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    fun validate(email:String, password:String):Int{
        when{
            email.isEmpty() -> return EMAIL_EMPTY
            !email.contains(Regex(emailRegex)) -> return EMAIL_INCORRECT
        }
        when{
            password.isEmpty() -> return PASSWORD_EMPTY
            password.length < 4 -> return PASSWORD_TO_SHORT
        }
        return FORM_VALID
    }
    companion object {
        val EMAIL_INCORRECT = 1
        val EMAIL_EMPTY = 2
        val PASSWORD_EMPTY = 3
        val PASSWORD_TO_SHORT = 4
        val FORM_VALID = 5
    }
}