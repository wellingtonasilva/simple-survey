package br.com.wsilva.simplesurvey.features.questiondetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.model.entity.ChoiceEntity
import kotlinx.android.synthetic.main.lay_question_detail_adapter.view.*

class QuestionDetailAdapter(private val context: Context,
                            private val list: List<ChoiceEntity>,
                            private val listener: QuestionDetailAdapterListener): RecyclerView.Adapter<QuestionDetailAdapter.ViewHolder>() {

    interface QuestionDetailAdapterListener {
        fun OnClickListener(choiceEntity: ChoiceEntity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    {
        val view = LayoutInflater.from(context).inflate(R.layout.lay_question_detail_adapter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int)
    {
        val entity = list[position]
        holder.lblChoice.text = entity.choice
        holder.content.setOnClickListener { listener.OnClickListener(entity) }
    }

    override fun getItemCount(): Int
    {
        return list.size
    }


    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val content = itemView.content
        val lblChoice = itemView.lblChoice
    }
}