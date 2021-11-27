package github.sun5066.adventuretime

import android.app.Application
import android.content.Context
import github.sun5066.adventuretime.di.networkModule
import github.sun5066.adventuretime.di.useCaseModule
import github.sun5066.adventuretime.di.viewModelModule
import org.koin.core.context.startKoin

class AdventureTimeApplication: Application() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: AdventureTimeApplication

        fun getApplicationContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(viewModelModule, networkModule, useCaseModule)
        }
    }
}