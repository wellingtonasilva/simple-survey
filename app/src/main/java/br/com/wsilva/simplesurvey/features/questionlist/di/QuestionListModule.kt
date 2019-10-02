package br.com.wsilva.simplesurvey.features.questionlist.di

import br.com.wsilva.simplesurvey.di.AppQuestionRepository
import br.com.wsilva.simplesurvey.di.AppSchedulers
import br.com.wsilva.simplesurvey.features.questionlist.QuestionListContract
import br.com.wsilva.simplesurvey.features.questionlist.QuestionListPresenter
import br.com.wsilva.simplesurvey.model.repository.QuestionRepository
import br.com.wsilva.simplesurvey.service.RestApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class QuestionListModule(private val view: QuestionListContract.View) {

    @Provides
    fun providesQuestionListView(): QuestionListContract.View = view

    @Provides
    fun providesQuestionListPresenter(view: QuestionListContract.View,
                                      bag: CompositeDisposable,
                                      schedulers: AppSchedulers,
                                      appQuestionRepository: AppQuestionRepository
    ): QuestionListContract.Presenter {
        return QuestionListPresenter(view, bag, schedulers, appQuestionRepository)
    }
}