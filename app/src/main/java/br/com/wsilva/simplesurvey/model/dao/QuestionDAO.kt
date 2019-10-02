package br.com.wsilva.simplesurvey.model.dao

import androidx.room.*
import br.com.wsilva.simplesurvey.core.BasicDAO
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity

@Dao
interface QuestionDAO: BasicDAO<QuestionEntity> {

    @Query("SELECT * FROM question")
    override fun listAll(): List<QuestionEntity>

    @Query("SELECT * FROM question WHERE id = :id")
    override fun get(id: Long): QuestionEntity

    @Delete
    override fun delete(entity: QuestionEntity): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun insert(entity: QuestionEntity): Long

    @Update
    override fun update(entity: QuestionEntity): Int

    @Query("SELECT COUNT(1) FROM question WHERE id = :id")
    fun exist(id: Long) : Int
}