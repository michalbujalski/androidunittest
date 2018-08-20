package com.mb.simpleapp

import com.mb.simpleapp.domain.users.UserInteractor
import com.mb.simpleapp.features.login.LoginPresenter
import com.mb.simpleapp.features.login.LoginPresenterImpl
import com.mb.simpleapp.features.login.LoginView
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import tea.Program

class LoginPresenterTests {
    @Mock lateinit var userInteractor: UserInteractor
    @Mock lateinit var presenter:LoginPresenter
    @Mock lateinit var view:LoginView

    @JvmField @Rule val rule:RxSchedulerRule = RxSchedulerRule()

    @Before fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = LoginPresenterImpl(userInteractor,view, Program(AndroidSchedulers.mainThread()))
    }

    @Test fun test_successfulLogin(){
        whenever(userInteractor.login(anyString(),anyString())).thenReturn(Single.just(true))
        presenter.initLogin("tom@test.com","someLongPass")
        verify(view).showProgress()
        verify(view).hideProgress()
        verify(view).onLoginSuccess()
    }

    @Test fun test_validation(){
        presenter.initLogin("","someLongPass")
        verify(view).showEmailEmptyError()

        presenter.initLogin("tom@test","someLongPass")
        verify(view).showImproperEmailFormat()

        presenter.initLogin("tom@test.com","as")
        verify(view).showPasswordToShort()

        presenter.initLogin("tom@test.com","")
        verify(view).showPasswordEmpty()
    }

    @Test fun test_errorHandling(){
        val errMsg = "Improper argument"
        whenever(userInteractor.login(anyString(),anyString()))
                .thenReturn(Single.error(IllegalArgumentException(errMsg)))
        presenter.initLogin("tom@example.com","someLongPass")
        verify(view).onLoginError(errMsg)
    }
}