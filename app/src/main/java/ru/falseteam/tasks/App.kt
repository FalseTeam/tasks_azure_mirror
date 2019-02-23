package ru.falseteam.tasks

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        initRealm()
    }

    private fun initRealm() {
        val config = RealmConfiguration.Builder()
                .name("main.realm")
                .build()

        Realm.init(this)
        Realm.setDefaultConfiguration(config)
    }
}