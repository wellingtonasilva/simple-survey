package br.com.wsilva.simplesurvey.constants

class AppConstants {
    companion object {
        const val BASE_URL = "https://private-bbbe9-blissrecruitmentapi.apiary-mock.com/"
        const val URL_HEALTH_STATUS = "health"
        const val URL_QUESTIONS = "questions"
        const val URL_QUESTION = URL_QUESTIONS + "/{id}"
        const val HEALTH_STATUS_OK = "OK"
    }
}