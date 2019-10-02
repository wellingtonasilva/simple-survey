package br.com.wsilva.simplesurvey.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_choice")
data class ChoiceEntity(@PrimaryKey(autoGenerate = true) var id: Long = 0,
                        @ColumnInfo(name = "question_id") val questionId: Long,
                        @ColumnInfo(name = "choice") val choice: String,
                        @ColumnInfo(name = "votes") val votes: Int)