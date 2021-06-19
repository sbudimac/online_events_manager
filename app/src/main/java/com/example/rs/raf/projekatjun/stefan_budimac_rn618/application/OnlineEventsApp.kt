package com.example.rs.raf.projekatjun.stefan_budimac_rn618.application

import android.app.Application
import com.example.rs.raf.projekatjun.stefan_budimac_rn618.modules.eventModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber

class OnlineEventsApp : Application() {

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {
        initTimber()
        initKoin()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKoin() {
        val modules = listOf(
            eventModule
        )
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@OnlineEventsApp)
            androidFileProperties()
            fragmentFactory()
            modules(modules)
        }
    }
}