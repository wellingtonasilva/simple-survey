package br.com.wsilva.simplesurvey.model.repository

import br.com.wsilva.simplesurvey.core.BasicRepository
import br.com.wsilva.simplesurvey.model.dao.ChoiceDAO
import br.com.wsilva.simplesurvey.model.dto.ChoiceDTO
import br.com.wsilva.simplesurvey.model.entity.ChoiceEntity

class ChoiceRepository(private val dao: ChoiceDAO): BasicRepository<ChoiceEntity>(dao) {

    override fun listAll(): List<ChoiceEntity>  = dao.listAll()
    override fun get(id: Long): ChoiceEntity = dao.get(id)
    override fun delete(entity: ChoiceEntity): Int = dao.delete(entity)
    override fun insert(entity: ChoiceEntity): Long  = dao.insert(entity)
    override fun update(entity: ChoiceEntity): Int  = dao.update(entity)

    fun save(questionId: Long, choiceDTO: ChoiceDTO): Long {
        var entity = get(questionId)
        if (entity == null) {
            entity = ChoiceEntity(questionId = questionId, choice = choiceDTO.choice,
                votes = choiceDTO.votes)
            return dao.insert(entity)
        }
        return entity.id
    }
}