package br.com.wsilva.simplesurvey.features.questionlist

import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.wsilva.simplesurvey.AppApplication
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.constants.AppConstants
import br.com.wsilva.simplesurvey.core.BasicFragment
import br.com.wsilva.simplesurvey.di.AppModule
import br.com.wsilva.simplesurvey.features.noconnection.NoConnectionActivity
import br.com.wsilva.simplesurvey.features.questiondetail.QuestionDetailActivity
import br.com.wsilva.simplesurvey.features.questionlist.di.DaggerQuestionListComponent
import br.com.wsilva.simplesurvey.features.questionlist.di.QuestionListModule
import br.com.wsilva.simplesurvey.features.share.ShareActivity
import br.com.wsilva.simplesurvey.model.entity.QuestionEntity
import br.com.wsilva.simplesurvey.util.CheckConnectionReceiver
import kotlinx.android.synthetic.main.lay_question_list_fragment.*
import kotlinx.android.synthetic.main.lay_question_list_fragment.view.*
import javax.inject.Inject

class QuestionListFragment: BasicFragment(), QuestionListContract.View,
    QuestionListAdapter.QuestionListAdapterListener, CheckConnectionReceiver.CheckConnectionListener {

    @Inject
    lateinit var presenter: QuestionListContract.Presenter
    val broadcastReceiver = CheckConnectionReceiver(this)

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
        view.fab.setOnClickListener { showShare() }
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater?)
    {
        val inflater = inflater
        inflater?.inflate(R.menu.menu_search, menu)
        val searchView =  (menu.findItem(R.id.action_search).actionView as SearchView)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean
            {
                presenter.onQueryTextSubmit(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean
            {
                return false
            }
        })
    }

    override fun onResume() {
        super.onResume()
        presenter.listAll()
        context?.registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    override fun onPause() {
        super.onPause()
        context?.unregisterReceiver(broadcastReceiver)
    }

    override fun showQuestion(list: List<QuestionEntity>) {
        recyclerView.setHasFixedSize(true)
        val linearLayoutManager =  LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = QuestionListAdapter(context!!, list, this@QuestionListFragment)
    }

    override fun showShare() {
        val intent = Intent(context, ShareActivity::class.java)
        intent.putExtra(AppConstants.QUESTION_FILTER, "")
        intent.putExtra(AppConstants.SHARE_TYPE, AppConstants.SHARE_TYPE_FILTER)
        startActivity(intent)
    }

    override fun OnClickListener(questionEntity: QuestionEntity) {
        showQuestionDetail(questionEntity.id)
    }

    override fun showQuestionDetail(questionId: Long) {
        val intent = Intent(context, QuestionDetailActivity::class.java)
        val bundle = Bundle()
        bundle.putLong(AppConstants.QUESTION_ID, questionId)
        intent.putExtras(bundle)
        startActivity(intent)
    }

    override fun OnConnectionChange(isOnline: Boolean) {
        if (!isOnline) {
            val intent = Intent(context, NoConnectionActivity::class.java)
            startActivity(intent)
        }
    }
}