package br.com.wsilva.simplesurvey.core

import androidx.paging.DataSource

abstract class BasicRepository<U, T>(private val dao: BasicDAO<U, T>) {
    abstract fun listAll() : DataSource.Factory<U, T>
    abstract fun get(id : Long) : T
    abstract fun delete(entity : T) : Int
    abstract fun insert(entity : T) : Long
    abstract fun update(entity : T) : Int
}