package br.com.wsilva.simplesurvey.features.questiondetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.wsilva.simplesurvey.AppApplication
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.constants.AppConstants
import br.com.wsilva.simplesurvey.core.BasicFragment
import br.com.wsilva.simplesurvey.di.AppModule
import br.com.wsilva.simplesurvey.features.noconnection.NoConnectionActivity
import br.com.wsilva.simplesurvey.features.questiondetail.di.DaggerQuestionDetailComponent
import br.com.wsilva.simplesurvey.features.questiondetail.di.QuestionDetailModule
import br.com.wsilva.simplesurvey.features.share.ShareActivity
import br.com.wsilva.simplesurvey.model.entity.ChoiceEntity
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity
import br.com.wsilva.simplesurvey.util.CheckConnectionReceiver
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.lay_question_detail_fragment.*
import kotlinx.android.synthetic.main.lay_question_detail_fragment.view.*
import javax.inject.Inject

class QuestionDetailFragment: BasicFragment(), QuestionDetailContract.View,
    QuestionDetailAdapter.QuestionDetailAdapterListener, CheckConnectionReceiver.CheckConnectionListener {

    @Inject
    lateinit var presenter: QuestionDetailContract.Presenter
    val broadcastReceiver = CheckConnectionReceiver(this)

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
            .questionDetailModule(QuestionDetailModule(this, arguments?.getLong(AppConstants.QUESTION_ID)!!))
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_question_detail_fragment, container, false)
        view.btnShare.setOnClickListener { showShare(presenter.questionId) }

        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.loadQuestion(presenter.questionId)
        presenter.loadChoises(presenter.questionId)
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
        Picasso.get()
            .load(questionEntity.imageUrl)
            .into(imgURL)
        lblQuestionDescription.text = questionEntity.question
    }

    override fun OnClickListener(choiceEntity: ChoiceEntity) {
    }

    override fun showShare(questionId: Long) {
        val intent = Intent(context, ShareActivity::class.java)
        intent.putExtra(AppConstants.SHARE_TYPE, AppConstants.SHARE_TYPE_QUESTION_ID)
        intent.putExtra(AppConstants.QUESTION_ID, questionId)
        startActivity(intent)
    }

    override fun OnConnectionChange(isOnline: Boolean) {
        if (!isOnline) {
            val intent = Intent(context, NoConnectionActivity::class.java)
            startActivity(intent)
        }
    }
}