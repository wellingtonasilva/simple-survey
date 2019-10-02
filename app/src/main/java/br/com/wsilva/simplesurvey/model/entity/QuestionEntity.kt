package br.com.wsilva.simplesurvey.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question")
data class QuestionEntity(@PrimaryKey var id: Long = 0,
                          @ColumnInfo(name = "question") val question: String,
                          @ColumnInfo(name = "image_url") val imageUrl: String,
                          @ColumnInfo(name = "thumb_url") val thumbUrl: String,
                          @ColumnInfo(name = "published_at") val published: String)