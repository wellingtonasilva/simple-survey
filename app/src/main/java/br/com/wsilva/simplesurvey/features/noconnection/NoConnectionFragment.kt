package br.com.wsilva.simplesurvey.features.noconnection

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.wsilva.simplesurvey.R
import br.com.wsilva.simplesurvey.core.BasicFragment
import br.com.wsilva.simplesurvey.util.CheckConnectionReceiver

class NoConnectionFragment: BasicFragment(), CheckConnectionReceiver.CheckConnectionListener {
    val broadcastReceiver = CheckConnectionReceiver(this)

    companion object {
        val TAG: String = "NoConnectionFragment"
        fun newInstance(): NoConnectionFragment {
            return NoConnectionFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            context?.registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context?.registerReceiver(broadcastReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater?.inflate(R.layout.lay_no_connection_fragment, container, false)

        return view
    }

    override fun onDestroy() {
        context?.unregisterReceiver(broadcastReceiver)
        super.onDestroy()
    }

    override fun OnConnectionChange(isOnline: Boolean) {
        if (isOnline) {
            activity?.finish()
        }
    }
}