package br.com.wsilva.simplesurvey.core

import io.reactivex.Flowable

interface BasicDAO<T> {
    fun listAll() : Flowable<List<T>>
    fun get(id: Long) : T
    fun delete(entity: T) : Int
    fun insert(entity: T) : Long
    fun update(entity: T) : Int
}