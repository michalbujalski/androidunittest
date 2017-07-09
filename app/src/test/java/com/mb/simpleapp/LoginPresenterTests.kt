package com.mb.simpleapp

import com.mb.simpleapp.domain.UserInteractor
import com.mb.simpleapp.features.login.LoginPresenter
import com.mb.simpleapp.features.login.LoginPresenterImpl
import com.mb.simpleapp.features.login.LoginView
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.anyString

class LoginPresenterTests {
    lateinit var userInteractor:UserInteractor
    lateinit var presenter:LoginPresenter
    lateinit var view:LoginView

    @Before fun setUp(){
        userInteractor = mock(UserInteractor::class.java)
        view = mock(LoginView::class.java)
        presenter = LoginPresenterImpl(userInteractor,view)
    }
}