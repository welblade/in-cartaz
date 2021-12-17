package com.github.welblade.incartaz

import android.app.Application
import com.github.welblade.incartaz.data.di.DataModules
import com.github.welblade.incartaz.domain.di.DomainModules
import com.github.welblade.incartaz.presentation.di.PresentationModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
        }
        DataModules.load()
        DomainModules.load()
        PresentationModules.load()
    }
}