package br.com.wsilva.simplesurvey.model.dto

import com.google.gson.annotations.SerializedName

data class ChoiceDTO(@SerializedName("choice") val choice: String,
                     @SerializedName("votes") val votes: Int)