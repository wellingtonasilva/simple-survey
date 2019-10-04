package br.com.wsilva.simplesurvey.model.dao

import androidx.room.*
import br.com.wsilva.simplesurvey.core.BasicDAO
import br.com.wsilva.simplesurvey.model.entity.ChoiceEntity

@Dao
interface ChoiceDAO: BasicDAO<ChoiceEntity> {

    @Query("SELECT * FROM question_choice")
    override fun listAll(): List<ChoiceEntity>

    @Query("SELECT * FROM question_choice WHERE question_id = :questionId")
    fun listByQuestionId(questionId: Long): List<ChoiceEntity>

    @Query("SELECT * FROM question_choice WHERE id = :id")
    override fun get(id: Long): ChoiceEntity

    @Query("SELECT * FROM question_choice WHERE question_id = :questionId AND choice = :choice")
    fun getByQuestionIdAndChoice(questionId: Long, choice: String): ChoiceEntity

    @Delete
    override fun delete(entity: ChoiceEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(entity: ChoiceEntity): Long

    @Update
    override fun update(entity: ChoiceEntity): Int

    @Query("SELECT COUNT(1) FROM question_choice WHERE question_id = :questionId")
    fun exist(questionId: Long) : Int
}