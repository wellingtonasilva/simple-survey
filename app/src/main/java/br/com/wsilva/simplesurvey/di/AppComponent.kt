package br.com.wsilva.simplesurvey.di

import android.app.Application
import br.com.wsilva.simplesurvey.model.db.AppDatabase
import dagger.Component
import retrofit2.Retrofit

@Component(modules = [AppModule::class, AppDatabaseModule::class])
interface AppComponent {
    fun application(): Application
    fun database(): AppDatabase
    fun retrofit(): Retrofit
    fun schedulers(): AppSchedulers
}