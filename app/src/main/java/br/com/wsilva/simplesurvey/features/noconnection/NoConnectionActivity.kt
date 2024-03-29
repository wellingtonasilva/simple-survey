package br.com.wsilva.simplesurvey.features.noconnection

import android.os.Bundle
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.core.BasicActivity
import br.com.wsilva.simplesurvey.util.AppUtils

class NoConnectionActivity: BasicActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_basic_activity)
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?)
    {
        val fragmentManager = supportFragmentManager
        var fragment = fragmentManager.findFragmentByTag(NoConnectionFragment.TAG)
        if (fragment == null) {
            fragment = NoConnectionFragment.newInstance()
        }
        if (savedInstanceState == null) {
            fragment.arguments = intent.extras
        } else {
            fragment.arguments = savedInstanceState
        }
        addFragmentToActivity(fragmentManager, fragment, R.id.frameLayout, NoConnectionFragment.TAG)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!AppUtils.isConnected(this)) {
            moveTaskToBack(true)
            finishAndRemoveTask()
        }
    }
}