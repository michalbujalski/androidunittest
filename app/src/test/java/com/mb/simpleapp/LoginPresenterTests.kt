package com.mb.simpleapp

import com.mb.simpleapp.domain.users.UserInteractor
import com.mb.simpleapp.features.login.LoginPresenter
import com.mb.simpleapp.features.login.LoginPresenterImpl
import com.mb.simpleapp.features.login.LoginView
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

class LoginPresenterTests {
    private lateinit var userInteractor: UserInteractor
    private lateinit var presenter:LoginPresenter
    private lateinit var view:LoginView

    @JvmField @Rule val rule:RxSchedulerRule = RxSchedulerRule()

    @Before fun setUp(){
        userInteractor = mock(UserInteractor::class.java)
        view = mock(LoginView::class.java)
        presenter = LoginPresenterImpl(userInteractor,view)
    }

    @Test fun test_successfulLogin(){
        whenever(userInteractor.login(anyString(),anyString())).thenReturn(Completable.complete())
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
                .thenReturn(Completable.error(IllegalArgumentException(errMsg)))
        presenter.initLogin("tom@example.com","someLongPass")
        verify(view).onLoginError(errMsg)
    }
}