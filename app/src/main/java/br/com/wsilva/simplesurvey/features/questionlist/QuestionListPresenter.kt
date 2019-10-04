package br.com.wsilva.simplesurvey.features.questionlist

import br.com.wsilva.simplesurvey.di.AppQuestionRepository
import br.com.wsilva.simplesurvey.di.AppSchedulers
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class QuestionListPresenter: QuestionListContract.Presenter {

    private val view: QuestionListContract.View
    private val bag: CompositeDisposable
    private val schedulers: AppSchedulers
    private val repository: AppQuestionRepository

    @Inject
    constructor(view: QuestionListContract.View,
                bag: CompositeDisposable,
                schedulers: AppSchedulers,
                appQuestionRepository: AppQuestionRepository
    ) {
        this.view = view
        this.bag = bag
        this.schedulers = schedulers
        this.repository = appQuestionRepository
    }

    override fun onDestroy() {
        bag.clear()
    }

    override fun onCreate() {
    }

    override fun listAll() {
        bag.add(
            repository
                .getQuestions(10, 0, "")
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { view.showQuestion(it) }
        )
    }

    override fun onQueryTextSubmit(query: String) {
        bag.add(
            repository
                .getQuestions(10, 0, query)
                .observeOn(schedulers.ui())
                .subscribeOn(schedulers.io())
                .subscribe { view.showQuestion(it) }
        )
    }
}