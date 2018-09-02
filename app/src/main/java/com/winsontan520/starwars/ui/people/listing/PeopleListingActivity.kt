package com.winsontan520.starwars.ui.people.listing

import android.os.Bundle
import com.winsontan520.starwars.R
import com.winsontan520.starwars.ui.base.BaseActivity
import kotlinx.android.synthetic.main.appbar.*

class PeopleListingActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_listing)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, PeopleListingFragment.newInstance())
                    .commitNow()
        }

        tv_title.text = getString(R.string.people_listing_title)
    }
}