package br.com.wsilva.simplesurvey.features.share

import android.os.Bundle
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.core.BasicActivity
import br.com.wsilva.simplesurvey.features.questiondetail.QuestionDetailFragment

class ShareActivity: BasicActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_basic_activity)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?)
    {
        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentByTag(ShareFragment.TAG)
        if (fragment == null) {
            fragment = ShareFragment.newInstance()
        }
        if (savedInstanceState == null) {
            fragment.arguments = intent.extras
        } else {
            fragment.arguments = savedInstanceState
        }
        addFragmentToActivity(fragmentManager, fragment, R.id.frameLayout, ShareFragment.TAG)
    }
}