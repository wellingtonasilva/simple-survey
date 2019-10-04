package br.com.wsilva.simplesurvey.constants

class AppConstants {
    companion object {
        const val BASE_URL = "https://private-bbbe9-blissrecruitmentapi.apiary-mock.com/"
        const val URL_HEALTH_STATUS = "health"
        const val URL_QUESTIONS = "questions"
        const val URL_QUESTION = URL_QUESTIONS + "/{id}"
        const val HEALTH_STATUS_OK = "OK"
        const val QUESTION_ID = "QUESTION_ID"
        const val QUESTION_FILTER = "QUESTION_FILTER"
        const val SHARE_TYPE_QUESTION_ID = "SHARE_TYPE_QUESTION_ID"
        const val SHARE_TYPE_FILTER = "SHARE_TYPE_FILTER"
        const val SHARE_TYPE = "SHARE_TYPE"
    }
}