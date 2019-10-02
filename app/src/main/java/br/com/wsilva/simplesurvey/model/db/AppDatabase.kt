package br.com.wsilva.simplesurvey.model.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.wsilva.simplesurvey.model.dao.ChoiceDAO
import br.com.wsilva.simplesurvey.model.dao.QuestionDAO
import br.com.wsilva.simplesurvey.model.entity.ChoiceEntity
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity

@Database(version = 1,
    entities = [
        QuestionEntity::class,
        ChoiceEntity::class
    ])
abstract class AppDatabase: RoomDatabase() {
    abstract fun getQuestionDAO(): QuestionDAO
    abstract fun getChoiceDAO(): ChoiceDAO
}