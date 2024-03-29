package br.com.wsilva.simplesurvey.features.questiondetail

import br.com.wsilva.simplesurvey.core.BasicPresenter
import br.com.wsilva.simplesurvey.model.entity.ChoiceEntity
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity

interface QuestionDetailContract {

    interface View {
        fun showQuestion(questionEntity: QuestionEntity)
        fun showChoises(list: List<ChoiceEntity>)
        fun showShare(questionId: Long)
    }

    interface Presenter: BasicPresenter {
        var questionId: Long
        fun loadQuestion(questionId: Long)
        fun loadChoises(questionId: Long)
    }
}