package br.com.wsilva.simplesurvey.features.questionlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.core.BasicFragment

class QuestionListFragment: BasicFragment(), QuestionListContract.View {

    companion object {
        val TAG: String = "QuestionListFragment"
        fun newInstance(): QuestionListFragment {
            return QuestionListFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_question_list_fragment, container, false)

        return view
    }
}