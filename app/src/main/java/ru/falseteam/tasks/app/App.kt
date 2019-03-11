package ru.falseteam.tasks.app

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class App : Application() {
    companion object {
        lateinit var dagger: AppComponent
            private set
    }

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        initDagger()
    }

    private fun initDagger() {
        dagger = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
    }
}