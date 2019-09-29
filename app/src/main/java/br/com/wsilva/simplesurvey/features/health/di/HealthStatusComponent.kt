package br.com.wsilva.simplesurvey.features.health.di

import br.com.wsilva.simplesurvey.di.AppModule
import br.com.wsilva.simplesurvey.features.health.HealthStatusFragment
import dagger.Component

@Component(modules = [HealthStatusModule::class, AppModule::class])
interface HealthStatusComponent {
    fun inject(fragment: HealthStatusFragment)
}