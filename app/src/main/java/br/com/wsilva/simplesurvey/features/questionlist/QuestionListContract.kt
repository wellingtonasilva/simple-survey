package br.com.wsilva.simplesurvey.features.questionlist

import br.com.wsilva.simplesurvey.core.BasicPresenter
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity

interface QuestionListContract {
    interface View {
        fun showQuestion(list: List<QuestionEntity>)
        fun showQuestionDetail(questionId: Long)
        fun showShare()
    }

    interface Presenter: BasicPresenter {
        fun listAll()
    }
}