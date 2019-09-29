package br.com.wsilva.simplesurvey.features.health

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.wsilva.simplesurvey.AppApplication
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.core.BasicFragment
import br.com.wsilva.simplesurvey.di.AppModule
import br.com.wsilva.simplesurvey.features.health.di.DaggerHealthStatusComponent
import br.com.wsilva.simplesurvey.features.health.di.HealthStatusModule
import kotlinx.android.synthetic.main.lay_health_status_fragment.view.*
import javax.inject.Inject

class HealthStatusFragment: BasicFragment(), HealthStatusContract.View {

    @Inject
    lateinit var presenter: HealthStatusContract.Presenter

    companion object {
        val TAG: String = "HealthStatusFragment"
        fun newInstance(): HealthStatusFragment {
            return HealthStatusFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerHealthStatusComponent
            .builder()
            .appModule(AppModule(AppApplication.appComponent.application()))
            .healthStatusModule(HealthStatusModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_health_status_fragment, container, false)
        view.btnPleaseTryAgain.setOnClickListener { presenter.checkHealthStatus() }

        return view
    }

    override fun showQuestionList() {
        Toast.makeText(context, "showQuestionList", Toast.LENGTH_SHORT).show()
    }
}