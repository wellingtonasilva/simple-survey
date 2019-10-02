package br.com.wsilva.simplesurvey.di

import br.com.wsilva.simplesurvey.model.dto.QuestionDTO
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity
import br.com.wsilva.simplesurvey.model.repository.ChoiceRepository
import br.com.wsilva.simplesurvey.model.repository.QuestionRepository
import br.com.wsilva.simplesurvey.service.RestApi
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class AppQuestionRepository {

    val restApi: RestApi
    val bag: CompositeDisposable
    val schedulers: AppSchedulers
    val questionRepository: QuestionRepository
    val choiceRepository: ChoiceRepository

    @Inject
    constructor(restApi: RestApi, bag: CompositeDisposable, schedulers: AppSchedulers,
                questionRepository: QuestionRepository, choiceRepository: ChoiceRepository) {
        this.restApi = restApi
        this.bag = bag
        this.schedulers = schedulers
        this.questionRepository = questionRepository
        this.choiceRepository = choiceRepository
    }


    fun getQuestions(limit: Int, offset: Int, filter: String): Observable<List<QuestionEntity>> {
        return restApi
            .getQuestions(limit, offset, filter)
            .flatMap { saveQuestion(it) }
            .flatMap { listQuestion() }
    }

    fun saveQuestion(list: List<QuestionDTO>): Observable<List<QuestionDTO>> {
        return Observable.create<List<QuestionDTO>> {
            list.forEach { question ->
                this.questionRepository.save(question)
            }
            it.onNext(list)
            it.onComplete()
        }
    }

    fun listQuestion(): Observable<List<QuestionEntity>> {
        return Observable.create<List<QuestionEntity>> {
            it.onNext(this.questionRepository.listAll())
            it.onComplete()
        }
    }
}