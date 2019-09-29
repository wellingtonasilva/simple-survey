package br.com.wsilva.simplesurvey.features.health.di

import br.com.wsilva.simplesurvey.di.AppSchedulers
import br.com.wsilva.simplesurvey.features.health.HealthStatusContract
import br.com.wsilva.simplesurvey.features.health.HealthStatusPresenter
import br.com.wsilva.simplesurvey.service.RestApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class HealthStatusModule(private val view: HealthStatusContract.View) {

    @Provides
    fun providesHealthStatusView(): HealthStatusContract.View = view

    @Provides
    fun providesHealthStatusPresenter(view: HealthStatusContract.View, bag: CompositeDisposable,
                                schedulers: AppSchedulers, restApi: RestApi
    ): HealthStatusContract.Presenter {
        return HealthStatusPresenter(view, bag, schedulers, restApi)
    }
}
