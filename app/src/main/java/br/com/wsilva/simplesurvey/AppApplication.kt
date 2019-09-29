package br.com.wsilva.simplesurvey

import android.app.Application
import br.com.wsilva.simplesurvey.di.AppComponent
import br.com.wsilva.simplesurvey.di.AppModule
import br.com.wsilva.simplesurvey.di.DaggerAppComponent

class AppApplication: Application() {

    companion object{
        @JvmStatic lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }
}