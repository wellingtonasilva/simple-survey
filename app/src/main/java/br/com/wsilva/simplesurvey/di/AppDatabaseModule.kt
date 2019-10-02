package br.com.wsilva.simplesurvey.di

import android.app.Application
import androidx.room.Room
import br.com.wsilva.simplesurvey.model.dao.ChoiceDAO
import br.com.wsilva.simplesurvey.model.dao.QuestionDAO
import br.com.wsilva.simplesurvey.model.db.AppDatabase
import br.com.wsilva.simplesurvey.model.repository.ChoiceRepository
import br.com.wsilva.simplesurvey.model.repository.QuestionRepository
import br.com.wsilva.simplesurvey.service.RestApi
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
class AppDatabaseModule {

    @Provides
    fun providesAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(application.applicationContext,
            AppDatabase::class.java, "dabase.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesQuestionDAO(appDatabase: AppDatabase): QuestionDAO {
        return appDatabase.getQuestionDAO()
    }

    @Provides
    fun providesQuestionRepository(questionDAO: QuestionDAO): QuestionRepository {
        return QuestionRepository(questionDAO)
    }

    @Provides
    fun providesChoiceDAO(appDatabase: AppDatabase): ChoiceDAO {
        return appDatabase.getChoiceDAO()
    }

    @Provides
    fun providesChoiceRepository(choiceDAO: ChoiceDAO): ChoiceRepository {
        return ChoiceRepository(choiceDAO)
    }

    @Provides
    fun providesAppQuestionRepository(restApi: RestApi, bag: CompositeDisposable,
                                      schedulers: AppSchedulers, questionRepository: QuestionRepository,
                                      choiceRepository: ChoiceRepository): AppQuestionRepository {
        return AppQuestionRepository(restApi, bag, schedulers, questionRepository, choiceRepository)
    }
}