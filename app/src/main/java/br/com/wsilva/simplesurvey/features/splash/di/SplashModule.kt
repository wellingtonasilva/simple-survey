package br.com.wsilva.simplesurvey.features.splash.di

import br.com.wsilva.simplesurvey.di.AppSchedulers
import br.com.wsilva.simplesurvey.features.splash.SplashContract
import br.com.wsilva.simplesurvey.features.splash.SplashPresenter
import br.com.wsilva.simplesurvey.service.RestApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class SplashModule (private val view: SplashContract.View) {

    @Provides
    fun providesSplashView(): SplashContract.View = view

    @Provides
    fun providesSplashPresenter(view: SplashContract.View, bag: CompositeDisposable,
                                schedulers: AppSchedulers, restApi: RestApi): SplashContract.Presenter {
        return SplashPresenter(view, bag, schedulers, restApi)
    }
}