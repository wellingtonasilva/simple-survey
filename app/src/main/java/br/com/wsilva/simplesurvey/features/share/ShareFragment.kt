package br.com.wsilva.simplesurvey.features.share

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.constants.AppConstants
import br.com.wsilva.simplesurvey.core.BasicFragment
import br.com.wsilva.simplesurvey.util.AppUtils
import kotlinx.android.synthetic.main.lay_share_fragment.*
import kotlinx.android.synthetic.main.lay_share_fragment.view.*

class ShareFragment: BasicFragment() {

    companion object {
        val TAG: String = "ShareFragment"
        fun newInstance(): ShareFragment {
            return ShareFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_share_fragment, container, false)
        view.btnDismiss.setOnClickListener { activity?.finish() }
        view.btnSend.setOnClickListener { AppUtils.share(context!!, edtLink.text.toString(),
            edtEmail.text.toString())
        }

        if (arguments?.get(AppConstants.SHARE_TYPE)!!.equals(AppConstants.SHARE_TYPE_FILTER)) {
            view.edtLink.setText(getString(R.string.app_deep_link_filter, arguments?.get(AppConstants.QUESTION_FILTER)))
        } else {
            view.edtLink.setText(getString(R.string.app_deep_link_id, arguments?.get(AppConstants.QUESTION_ID)))
        }

        return view
    }
}