package br.com.wsilva.simplesurvey.service

import br.com.wsilva.simplesurvey.constants.AppConstants
import br.com.wsilva.simplesurvey.model.dto.HealthDTO
import br.com.wsilva.simplesurvey.model.dto.QuestionDTO
import io.reactivex.Observable
import retrofit2.http.*

interface RestApi {

    @GET(AppConstants.URL_HEALTH_STATUS)
    fun getHealthStatus(): Observable<HealthDTO>

    @GET(AppConstants.URL_QUESTIONS)
    fun getQuestions(@Query("limit") limit: Int, @Query("offset") offset: Int,
                     @Query("filter") filter: String): Observable<QuestionDTO>

    @GET(AppConstants.URL_QUESTION)
    fun getQuestion(@Path("id") id: Long): Observable<QuestionDTO>

    @PUT(AppConstants.URL_QUESTION)
    fun updateQuestion(@Body question: QuestionDTO): Observable<Boolean>
}