package br.com.wsilva.simplesurvey.features.questiondetail

import br.com.wsilva.simplesurvey.di.AppQuestionRepository
import br.com.wsilva.simplesurvey.di.AppSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class QuestionDetailPresenter: QuestionDetailContract.Presenter {

    private val view: QuestionDetailContract.View
    private val bag: CompositeDisposable
    private val schedulers: AppSchedulers
    private val repository: AppQuestionRepository

    @Inject
    constructor(view: QuestionDetailContract.View,
                bag: CompositeDisposable,
                schedulers: AppSchedulers,
                appQuestionRepository: AppQuestionRepository
    ) {
        this.view = view
        this.bag = bag
        this.schedulers = schedulers
        this.repository = appQuestionRepository
    }

    override fun onCreate() {
    }

    override fun onDestroy() {
        bag.clear()
    }

    override fun loadChoises(questionId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadQuestion(questionId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}