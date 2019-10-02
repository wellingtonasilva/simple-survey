package br.com.wsilva.simplesurvey.features.questionlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lay_question_list_adapter.view.*

class QuestionListAdapter(private val context: Context,
                          private val list: List<QuestionEntity>,
                          private val listener: QuestionListAdapterListener): RecyclerView.Adapter<QuestionListAdapter.ViewHolder>() {

    interface QuestionListAdapterListener {
        fun OnClickListener(questionEntity: QuestionEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(context).inflate(R.layout.lay_question_list_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val entity = list[position]
        holder.lblQuestion.text = entity.question
        holder.content.setOnClickListener { listener.OnClickListener(entity) }
        Picasso.get()
            .load(entity.thumbUrl)
            .into(holder.imgThumb)
    }

    override fun getItemCount(): Int
    {
        return list.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val content = itemView.content
        val lblQuestion = itemView.lblQuestion
        val imgThumb = itemView.imgThumb
    }
}