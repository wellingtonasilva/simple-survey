package br.com.wsilva.simplesurvey.features.questionlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lay_question_list_adapter.view.*

class QuestionListViewHolder(view: View, listener: QuestionListAdapter.QuestionListAdapterListener) : RecyclerView.ViewHolder(view) {
    private val lblQuestion: TextView = itemView.lblQuestion
    private val imgThumb: ImageView = itemView.imgThumb
    private var question: QuestionEntity? = null

    companion object {
        fun create(parent: ViewGroup, listener: QuestionListAdapter.QuestionListAdapterListener): QuestionListViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.lay_question_list_adapter,
                parent, false)
            return QuestionListViewHolder(view, listener)
        }
    }

    init {
        view.setOnClickListener {
            question?.id?.let {
               listener.OnClickListener(question!!)
            }
        }
    }

    fun bind(question: QuestionEntity) {
        if (question == null) {
            showLoadingData()
        } else {
            showData(question)
        }
    }

    private fun showData(question: QuestionEntity) {
        this.question = question
        lblQuestion.text = question.question
        Picasso.get()
            .load(question.thumbUrl)
            .into(imgThumb.imgThumb)
    }

    private fun showLoadingData() {
        val resources = itemView.resources
        lblQuestion.text = resources.getText(R.string.app_loading)
    }
}