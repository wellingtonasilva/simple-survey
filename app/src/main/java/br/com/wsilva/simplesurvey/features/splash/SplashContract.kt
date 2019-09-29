package br.com.wsilva.simplesurvey.features.splash

import br.com.wsilva.simplesurvey.core.BasicPresenter
import br.com.wsilva.simplesurvey.model.dto.HealthDTO

interface SplashContract {
    interface View {
        fun showHealthStatus()
        fun showQuestionList()
    }

    interface Presenter: BasicPresenter {
        fun checkHealthStatus()
        fun doAfterCheckHealthStatus(healthDTO: HealthDTO)
    }
}