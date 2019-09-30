package br.com.wsilva.simplesurvey.features.questionlist

import android.os.Bundle
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.core.BasicActivity

class QuestionListActivity: BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_basic_activity)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?)
    {
        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentByTag(QuestionListFragment.TAG)
        if (fragment == null) {
            fragment = QuestionListFragment.newInstance()
        }
        if (savedInstanceState == null) {
            fragment.arguments = intent.extras
        } else {
            fragment.arguments = savedInstanceState
        }
        addFragmentToActivity(fragmentManager, fragment, R.id.frameLayout, QuestionListFragment.TAG)
    }
}