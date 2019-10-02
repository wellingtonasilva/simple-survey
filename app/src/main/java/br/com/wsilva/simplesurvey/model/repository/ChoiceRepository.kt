package br.com.wsilva.simplesurvey.model.repository

import br.com.wsilva.simplesurvey.core.BasicRepository
import br.com.wsilva.simplesurvey.model.dao.ChoiceDAO
import br.com.wsilva.simplesurvey.model.entity.ChoiceEntity

class ChoiceRepository(private val dao: ChoiceDAO): BasicRepository<ChoiceEntity>(dao) {

    override fun listAll(): List<ChoiceEntity>  = dao.listAll()
    override fun get(id: Long): ChoiceEntity = dao.get(id)
    override fun delete(entity: ChoiceEntity): Int = dao.delete(entity)
    override fun insert(entity: ChoiceEntity): Long  = dao.insert(entity)
    override fun update(entity: ChoiceEntity): Int  = dao.update(entity)
}