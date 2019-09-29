package br.com.wsilva.simplesurvey.model.dto

import com.google.gson.annotations.SerializedName

data class HealthDTO(@SerializedName("status") val status: String)