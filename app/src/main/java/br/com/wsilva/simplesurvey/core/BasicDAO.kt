package br.com.wsilva.simplesurvey.core

interface BasicDAO<T> {
    fun listAll() : List<T>
    fun get(id: Long) : T
    fun delete(entity: T) : Int
    fun insert(entity: T) : Long
    fun update(entity: T) : Int
}