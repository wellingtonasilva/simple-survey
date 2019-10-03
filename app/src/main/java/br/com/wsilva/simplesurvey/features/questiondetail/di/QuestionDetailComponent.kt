package br.com.wsilva.simplesurvey.features.questiondetail.di

import br.com.wsilva.simplesurvey.di.AppDatabaseModule
import br.com.wsilva.simplesurvey.di.AppModule
import br.com.wsilva.simplesurvey.features.questiondetail.QuestionDetailFragment
import dagger.Component

@Component(modules = [QuestionDetailModule::class, AppModule::class, AppDatabaseModule::class])
interface QuestionDetailComponent {
    fun inject(fragment: QuestionDetailFragment)
}