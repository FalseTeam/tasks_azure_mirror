package ru.falseteam.tasks.app

import android.app.Application

class App : Application() {
    companion object {
        lateinit var dagger: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
        dagger = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}