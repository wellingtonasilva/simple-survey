package br.com.wsilva.simplesurvey.features.questiondetail

import br.com.wsilva.simplesurvey.di.AppQuestionRepository
import br.com.wsilva.simplesurvey.di.AppSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class QuestionDetailPresenter @Inject constructor(override var questionId: Long,
                                                  private val view: QuestionDetailContract.View,
                                                  private val bag: CompositeDisposable,
                                                  private val schedulers: AppSchedulers,
                                                  private val repository: AppQuestionRepository) : QuestionDetailContract.Presenter {

    override fun onCreate() {
    }

    override fun onDestroy() {
        bag.clear()
    }

    override fun loadChoises(questionId: Long) {
        bag.add(
            repository
                .listChoicesByQuestionId(questionId)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { view.showChoises(it) }
        )
    }

    override fun loadQuestion(questionId: Long) {
        bag.add(
            repository
                .questionRepository
                .getQuestion(questionId)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { view.showQuestion(it) }
        )
    }

}