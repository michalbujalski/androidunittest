package com.mb.simpleapp.features.login

enum class ValidationResult{
    EMAIL_INCORRECT,
    EMAIL_EMPTY,
    PASSWORD_EMPTY,
    PASSWORD_TO_SHORT,
    FORM_VALID
}
class LoginFormValidator {
    private val emailRegex = "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"
    fun validate(email:String, password:String):ValidationResult{
        when{
            email.isEmpty() -> return ValidationResult.EMAIL_EMPTY
            !email.contains(Regex(emailRegex)) -> return ValidationResult.EMAIL_INCORRECT
        }
        when{
            password.isEmpty() -> return ValidationResult.PASSWORD_EMPTY
            password.length < 4 -> return ValidationResult.PASSWORD_TO_SHORT
        }
        return ValidationResult.FORM_VALID
    }
}