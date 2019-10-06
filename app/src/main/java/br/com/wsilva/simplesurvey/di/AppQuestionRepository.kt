package br.com.wsilva.simplesurvey.di

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import br.com.wsilva.simplesurvey.model.dto.QuestionDTO
import br.com.wsilva.simplesurvey.model.entity.ChoiceEntity
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


    fun getQuestions(limit: Int, offset: Int, filter: String): Observable<PagedList<QuestionEntity>> {
        return restApi
            .getQuestions(limit, offset, filter)
            .flatMap { saveQuestion(it) }
            .flatMap { saveChoices(it) }
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

    fun saveChoices(list: List<QuestionDTO>): Observable<Unit> {
        return Observable.create<Unit> {
           list.forEach {
               it.choices.forEach { item ->
                   choiceRepository.save(it.id, item)
               }
           }
            it.onNext(Unit)
            it.onComplete()
        }
    }

    fun listQuestion(): Observable<PagedList<QuestionEntity>> {
        return RxPagedListBuilder(this.questionRepository.listAll(), 10).buildObservable()
    }

    fun listChoicesByQuestionId(questionId: Long): Observable<List<ChoiceEntity>> {
        return Observable.create<List<ChoiceEntity>> {
            it.onNext(this.choiceRepository.listByQuestionId(questionId))
            it.onComplete()
        }
    }
}