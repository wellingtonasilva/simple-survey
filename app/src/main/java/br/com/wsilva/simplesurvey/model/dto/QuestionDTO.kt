package br.com.wsilva.simplesurvey.model.dto

import com.google.gson.annotations.SerializedName

data class QuestionDTO(@SerializedName("id") val id: Int,
                       @SerializedName("question") val question: String,
                       @SerializedName("image_url") val image_url: String,
                       @SerializedName("thumb_url") val thumb_url: String,
                       @SerializedName( "published_at") val published_at: String,
                       @SerializedName("choices") val choices: List<ChoiceDTO>)