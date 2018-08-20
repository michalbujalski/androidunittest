package com.mb.simpleapp.features.login

import android.util.Log
import com.mb.simpleapp.domain.users.UserInteractor
import com.mb.simpleapp.utils.*
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import tea.*


class LoginPresenterImpl(
        val userInteractor: UserInteractor,
        val view:LoginView,
        val program: Program
):LoginPresenter, Component {
    override fun initLogin(email: String, password: String) {
        program.accept(UpdatePassword(password))
        program.accept(UpdateEmail(email))
        program.accept(BeginSubmit())
    }

    data class LoginState(
            val email:String = "",
            val password:String = "",
            val isFormValid:Boolean = false,
            val isLoading:Boolean = false,
            val isSuccess:Boolean = false,
            val responseError:String? = null,
            val validationResult: ValidationResult = ValidationResult.FORM_VALID
    ): State()
    class UpdateEmail(val email:String) : Msg()
    class UpdatePassword(val password:String) : Msg()
    class OnValidation(val result: ValidationResult): Msg()
    class BeginSubmit: Msg()
    class Submit: Msg()
    class ValidateCmd(val email:String, val password: String): Cmd()
    class SubmitCmd(val email:String, val password: String): Cmd()
    class SubmitSuccess(): Msg()
    var programDisposable: Disposable

    init {
        programDisposable = program.init(LoginState(), this)

        program.accept(Init())
    }

    override fun update(msg: Msg, state: State): Pair<State, Cmd> {
        val state = state as LoginState
        return when(msg){
            is Init -> {
                Pair(state, None())
            }
            is UpdateEmail -> {
                Pair(state.copy(email = msg.email), None())
            }
            is UpdatePassword -> {
                Pair(state.copy(password = msg.password), None())
            }
            is BeginSubmit -> {
                Pair(state.copy(responseError = null),ValidateCmd(state.email, state.password))
            }
            is Submit -> {
                Pair(state.copy(isLoading = true, isSuccess = false),SubmitCmd(state.email, state.password))
            }
            is SubmitSuccess -> {
                Pair(state.copy(
                        isLoading = false, isSuccess = true, email = "", password = "", isFormValid = false),
                        None()
                )
            }
            is OnValidation -> {
                val isFormValid = msg.result == ValidationResult.FORM_VALID
                Pair(state
                        .copy(
                                validationResult = msg.result, isLoading = isFormValid
                        ),
                                if(isFormValid)
                                    SubmitCmd(state.email, state.password)
                                else
                                    None()
                )
            }
            is ErrorMsg -> {
                Pair(state.copy(responseError = msg.err.message), None())
            }
            else -> Pair(state,None())
        }
    }

    override fun render(state: State) {
        Log.v("aaa",state.toString())
        (state as LoginState).apply {
            view.setEmail(this.email)
            view.setPassword(this.password)
            if(isLoading){
                view.showProgress()
            }else{
                view.hideProgress()
            }
            if(this.isSuccess){
                view.showSuccess()
            }
            when (this.validationResult) {
                ValidationResult.EMAIL_EMPTY -> view.showEmailEmptyError()
                ValidationResult.PASSWORD_EMPTY -> view.showPasswordEmpty()
                ValidationResult.EMAIL_INCORRECT -> view.showImproperEmailFormat()
                ValidationResult.PASSWORD_TO_SHORT -> view.showPasswordToShort()
            }
        }
    }

    override fun call(cmd: Cmd): Single<Msg> {
        return when(cmd){
            is ValidateCmd -> Single.just(
                        OnValidation(LoginFormValidator().validate(cmd.email,cmd.password))
                )
            is SubmitCmd -> userInteractor
                        .login(cmd.email,cmd.password)
                        .map{SubmitSuccess()}
            else -> Single.just(Idle())
        }
    }
}