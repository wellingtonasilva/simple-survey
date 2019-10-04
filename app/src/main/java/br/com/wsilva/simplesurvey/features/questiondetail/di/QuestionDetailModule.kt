package br.com.wsilva.simplesurvey.features.questiondetail.di

import br.com.wsilva.simplesurvey.di.AppQuestionRepository
import br.com.wsilva.simplesurvey.di.AppSchedulers
import br.com.wsilva.simplesurvey.features.questiondetail.QuestionDetailContract
import br.com.wsilva.simplesurvey.features.questiondetail.QuestionDetailPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class QuestionDetailModule(private val view: QuestionDetailContract.View,
                           private val questionId: Long) {

    @Provides
    fun providesQuestionDetailView(): QuestionDetailContract.View = view

    @Provides
    fun providesQuestionDetailPresenter(view: QuestionDetailContract.View,
                                        bag: CompositeDisposable,
                                        schedulers: AppSchedulers,
                                        appQuestionRepository: AppQuestionRepository
    ): QuestionDetailContract.Presenter
    {
        return QuestionDetailPresenter(questionId, view, bag, schedulers, appQuestionRepository)
    }
}