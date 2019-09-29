package br.com.wsilva.simplesurvey.core

import io.reactivex.Scheduler

interface BasicSchedulers {
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
}