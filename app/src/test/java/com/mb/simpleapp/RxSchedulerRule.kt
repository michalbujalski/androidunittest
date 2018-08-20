package com.mb.simpleapp

import io.reactivex.schedulers.Schedulers
import io.reactivex.android.plugins.RxAndroidPlugins
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


class RxSchedulerRule:TestRule{
    override fun apply(base: Statement?, description: Description?): Statement {
        return object:Statement(){
            override fun evaluate(){
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
            }
        }
    }
}