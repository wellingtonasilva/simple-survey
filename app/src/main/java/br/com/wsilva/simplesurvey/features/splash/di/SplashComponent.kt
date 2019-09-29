package br.com.wsilva.simplesurvey.features.splash.di

import br.com.wsilva.simplesurvey.di.AppModule
import br.com.wsilva.simplesurvey.features.splash.SplashFragment
import dagger.Component

@Component(modules = [SplashModule::class, AppModule::class])
interface SplashComponent {
    fun inject(fragment: SplashFragment)
}