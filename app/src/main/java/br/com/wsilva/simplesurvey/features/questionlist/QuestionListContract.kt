package br.com.wsilva.simplesurvey.features.questionlist

import br.com.wsilva.simplesurvey.core.BasicPresenter
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity

interface QuestionListContract {
    interface View {
        fun showQuestion(list: List<QuestionEntity>)
    }

    interface Presenter: BasicPresenter {
        fun listAll()
    }
}