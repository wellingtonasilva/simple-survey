package br.com.wsilva.simplesurvey.features.questionlist

import android.content.Intent
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
import br.com.wsilva.simplesurvey.features.questiondetail.QuestionDetailActivity
import br.com.wsilva.simplesurvey.features.questionlist.di.DaggerQuestionListComponent
import br.com.wsilva.simplesurvey.features.questionlist.di.QuestionListModule
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity
import kotlinx.android.synthetic.main.lay_question_list_fragment.*
import javax.inject.Inject

class QuestionListFragment: BasicFragment(), QuestionListContract.View,
    QuestionListAdapter.QuestionListAdapterListener {

    @Inject
    lateinit var presenter: QuestionListContract.Presenter

    companion object {
        val TAG: String = "QuestionListFragment"
        fun newInstance(): QuestionListFragment {
            return QuestionListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerQuestionListComponent
            .builder()
            .appModule(AppModule(AppApplication.appComponent.application()))
            .questionListModule(QuestionListModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_question_list_fragment, container, false)

        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.listAll()
    }

    override fun showQuestion(list: List<QuestionEntity>) {
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager =  LinearLayoutManager(context)
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context,
            linearLayoutManager.orientation)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.addItemDecoration(dividerItemDecoration)
        recyclerView.adapter = QuestionListAdapter(context!!, list, this@QuestionListFragment)
    }

    override fun OnClickListener(questionEntity: QuestionEntity) {
        showQuestionDetail(questionEntity.id)
    }

    override fun showQuestionDetail(questionId: Long) {
        val intent = Intent(context, QuestionDetailActivity::class.java)
        startActivity(intent)
    }
}