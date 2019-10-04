package br.com.wsilva.simplesurvey.core

import android.os.Bundle
import br.com.wsilva.simplesurvey.R

class SearchableActivity: BasicActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lay_searchable_activity)
    }
}