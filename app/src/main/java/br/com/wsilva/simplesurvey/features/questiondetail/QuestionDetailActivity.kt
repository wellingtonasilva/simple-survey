package br.com.wsilva.simplesurvey.features.questiondetail

import android.os.Bundle
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.core.BasicActivity

class QuestionDetailActivity: BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_basic_activity)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?)
    {
        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentByTag(QuestionDetailFragment.TAG)
        if (fragment == null) {
            fragment = QuestionDetailFragment.newInstance()
        }
        if (savedInstanceState == null) {
            fragment.arguments = intent.extras
        } else {
            fragment.arguments = savedInstanceState
        }
        addFragmentToActivity(fragmentManager, fragment, R.id.frameLayout, QuestionDetailFragment.TAG)
    }
}