package br.com.wsilva.simplesurvey.model.repository

import androidx.paging.DataSource
import br.com.wsilva.simplesurvey.core.BasicRepository
import br.com.wsilva.simplesurvey.model.dao.QuestionDAO
import br.com.wsilva.simplesurvey.model.dto.QuestionDTO
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity
import io.reactivex.Observable

class QuestionRepository(private val dao: QuestionDAO): BasicRepository<Int, QuestionEntity>(dao) {

    override fun listAll(): DataSource.Factory<Int, QuestionEntity>  = dao.listAll()
    override fun get(id: Long): QuestionEntity = dao.get(id)
    override fun delete(entity: QuestionEntity): Int = dao.delete(entity)
    override fun insert(entity: QuestionEntity): Long  = dao.insert(entity)
    override fun update(entity: QuestionEntity): Int  = dao.update(entity)
    fun exist(id: Long): Boolean = dao.exist(id) > 0

    fun save(questionDTO: QuestionDTO): Long {
        var entity = get(questionDTO.id)
        if (entity == null) {
            entity = QuestionEntity(id = questionDTO.id,
                imageUrl = questionDTO.image_url,
                published = questionDTO.published_at,
                question = questionDTO.question,
                thumbUrl = questionDTO.thumb_url)
            return dao.insert(entity)
        }
        return entity.id
    }

    fun getQuestion(questionId: Long): Observable<QuestionEntity> {
        return Observable.create<QuestionEntity> {
            it.onNext(get(questionId))
            it.onComplete()
        }
    }
}