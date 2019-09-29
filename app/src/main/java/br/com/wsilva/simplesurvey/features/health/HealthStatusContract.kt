package br.com.wsilva.simplesurvey.features.health

import br.com.wsilva.simplesurvey.core.BasicPresenter
import br.com.wsilva.simplesurvey.model.dto.HealthDTO

interface HealthStatusContract {

    interface View {
        fun showQuestionList()
    }

    interface Presenter: BasicPresenter {
        fun checkHealthStatus()
        fun doAfterCheckHealthStatus(healthDTO: HealthDTO)
    }
}