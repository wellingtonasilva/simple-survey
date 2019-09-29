package br.com.wsilva.simplesurvey.di

import android.app.Application
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [AppModule::class])
interface AppComponent {
    fun application(): Application
    fun retrofit(): Retrofit
    fun schedulers(): AppSchedulers
}