package br.com.wsilva.simplesurvey.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class CheckConnectionReceiver(private val listener: CheckConnectionListener): BroadcastReceiver() {

    interface CheckConnectionListener {
        fun OnConnectionChange(isOnline: Boolean)
    }

    override fun onReceive(context: Context, intent: Intent) {
        listener.OnConnectionChange(AppUtils.isConnected(context))
    }
}