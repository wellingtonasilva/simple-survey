package br.com.wsilva.simplesurvey.features.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.wsilva.simplesurvey.AppApplication
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.core.BasicFragment
import br.com.wsilva.simplesurvey.di.AppModule
import br.com.wsilva.simplesurvey.features.splash.di.DaggerSplashComponent
import br.com.wsilva.simplesurvey.features.splash.di.SplashModule
import javax.inject.Inject

class SplashFragment: BasicFragment(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashContract.Presenter

    companion object {
        val TAG: String = "SplashFragment"
        fun newInstance(): SplashFragment {
            return SplashFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerSplashComponent
            .builder()
            .appModule(AppModule(AppApplication.appComponent.application()))
            .splashModule(SplashModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_splash_fragment, container, false)
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.checkHealthStatus()
    }

    override fun showHealthStatus() {
        Toast.makeText(context, "showHealthStatus", Toast.LENGTH_SHORT).show()
    }

    override fun showQuestionList() {
        Toast.makeText(context, "showQuestionList", Toast.LENGTH_SHORT).show()
    }
}