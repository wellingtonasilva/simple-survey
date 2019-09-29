package br.com.wsilva.simplesurvey.features.health

import br.com.wsilva.simplesurvey.constants.AppConstants
import br.com.wsilva.simplesurvey.di.AppSchedulers
import br.com.wsilva.simplesurvey.model.dto.HealthDTO
import br.com.wsilva.simplesurvey.service.RestApi
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class HealthStatusPresenter: HealthStatusContract.Presenter {

    private val view: HealthStatusContract.View
    private val bag: CompositeDisposable
    private val schedulers: AppSchedulers
    private val restApi: RestApi

    @Inject
    constructor(view: HealthStatusContract.View, bag: CompositeDisposable, schedulers: AppSchedulers,
                restApi: RestApi
    ) {
        this.view = view
        this.bag = bag
        this.schedulers = schedulers
        this.restApi = restApi
    }

    override fun onCreate() {
    }

    override fun onDestroy() {
        bag.clear()
    }

    override fun checkHealthStatus() {
        bag.add(
            this.restApi
                .getHealthStatus()
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui())
                .subscribe { doAfterCheckHealthStatus(it) }
        )
    }

    override fun doAfterCheckHealthStatus(healthDTO: HealthDTO) {
        if (healthDTO.status.equals(AppConstants.HEALTH_STATUS_OK)) {
            view.showQuestionList()
        }
    }
}