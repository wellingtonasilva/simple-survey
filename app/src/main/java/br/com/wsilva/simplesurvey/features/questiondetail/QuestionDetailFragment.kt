package br.com.wsilva.simplesurvey.features.questiondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.wsilva.simplesurvey.AppApplication
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.core.BasicFragment
import br.com.wsilva.simplesurvey.di.AppModule
import br.com.wsilva.simplesurvey.features.questiondetail.di.DaggerQuestionDetailComponent
import br.com.wsilva.simplesurvey.features.questiondetail.di.QuestionDetailModule
import br.com.wsilva.simplesurvey.model.entity.ChoiceEntity
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity
import kotlinx.android.synthetic.main.lay_question_detail_fragment.*
import javax.inject.Inject

class QuestionDetailFragment: BasicFragment(), QuestionDetailContract.View,
    QuestionDetailAdapter.QuestionDetailAdapterListener {

    @Inject
    lateinit var presenter: QuestionDetailContract.Presenter

    companion object {
        val TAG: String = "QuestionDetailFragment"
        fun newInstance(): QuestionDetailFragment {
            return QuestionDetailFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerQuestionDetailComponent
            .builder()
            .appModule(AppModule(AppApplication.appComponent.application()))
            .questionDetailModule(QuestionDetailModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_question_detail_fragment, container, false)

        return view
    }

    override fun showChoises(list: List<ChoiceEntity>) {
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager =  LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
            linearLayoutManager.orientation)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = QuestionDetailAdapter(context!!, list, this@QuestionDetailFragment)
    }

    override fun showQuestion(questionEntity: QuestionEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun OnClickListener(choiceEntity: ChoiceEntity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}