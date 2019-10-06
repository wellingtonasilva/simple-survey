package br.com.wsilva.simplesurvey.features.questionlist

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity

class QuestionListAdapter(private val listener: QuestionListAdapterListener): PagedListAdapter<QuestionEntity, QuestionListViewHolder>(DIFF_CALLBACK) {

    interface QuestionListAdapterListener {
        fun OnClickListener(questionEntity: QuestionEntity)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<QuestionEntity>() {
            // The ID property identifies when items are the same.
            override fun areItemsTheSame(oldItem: QuestionEntity, newItem: QuestionEntity) =
                oldItem.id == newItem.id

            // If you use the "==" operator, make sure that the object implements
            // .equals(). Alternatively, write custom data comparison logic here.
            override fun areContentsTheSame(
                oldItem: QuestionEntity, newItem: QuestionEntity) = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionListViewHolder {
       return QuestionListViewHolder.create(parent, listener)
    }

    override fun onBindViewHolder(holder: QuestionListViewHolder, position: Int) {
        val question = getItem(position)
        if (question != null) {
            holder.bind(question)
        }
    }
}