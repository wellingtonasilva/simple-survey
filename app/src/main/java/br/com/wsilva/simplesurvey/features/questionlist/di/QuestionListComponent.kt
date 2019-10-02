package br.com.wsilva.simplesurvey.features.questionlist.di

import br.com.wsilva.simplesurvey.di.AppDatabaseModule
import br.com.wsilva.simplesurvey.di.AppModule
import br.com.wsilva.simplesurvey.features.questionlist.QuestionListFragment
import dagger.Component

@Component(modules = [QuestionListModule::class, AppModule::class, AppDatabaseModule::class])
interface QuestionListComponent {
    fun inject(fragment: QuestionListFragment)
}