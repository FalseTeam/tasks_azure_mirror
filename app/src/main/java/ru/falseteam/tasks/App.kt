package ru.falseteam.tasks

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {
    companion object {
        lateinit var dagger: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()

        initRealm()
        initDagger()
    }

    private fun initRealm() {
        Realm.init(this)

        val config = RealmConfiguration.Builder()
                .name("main.realm")
                .build()

        Realm.setDefaultConfiguration(config)
    }

    private fun initDagger() {
        dagger = DaggerAppComponent.create()
    }
}