package br.com.wsilva.simplesurvey.core

import androidx.paging.DataSource

interface BasicDAO<U, T> {
    fun listAll() : DataSource.Factory<U, T>
    fun get(id: Long) : T
    fun delete(entity: T) : Int
    fun insert(entity: T) : Long
    fun update(entity: T) : Int
}