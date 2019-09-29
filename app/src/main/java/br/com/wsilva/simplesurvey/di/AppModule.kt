package br.com.wsilva.simplesurvey.di

import android.app.Application
import br.com.wsilva.simplesurvey.constants.AppConstants
import br.com.wsilva.simplesurvey.service.RestApi
import br.com.wsilva.simplesurvey.util.AppUtils
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit
import javax.inject.Named

@Module
class AppModule(val application: Application) {

    @Provides
    fun providesApplication(): Application = application

    @Provides
    @Named("baseUrl")
    fun providesBaseUrl() = AppConstants.BASE_URL

    @Provides
    fun providesRetrofit(@Named("baseUrl") url: String): Retrofit {
        return AppUtils.createRetrofit(url)
    }

    @Provides
    fun providesRestApi(retrofit: Retrofit): RestApi {
        return retrofit.create(RestApi::class.java)
    }

    @Provides
    fun providesBaseSchedulers() = AppSchedulers()

    @Provides
    fun providesCompositeDisposable() = CompositeDisposable()
}