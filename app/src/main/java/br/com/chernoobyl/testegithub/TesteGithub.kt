package br.com.chernoobyl.testegithub

import android.app.Application
import br.com.chernoobyl.testegithub.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TesteGithub : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidLogger()
            androidContext(this@TesteGithub)
            modules(appModules)
        }
    }
}