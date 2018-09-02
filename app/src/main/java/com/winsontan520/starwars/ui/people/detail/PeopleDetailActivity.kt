package com.winsontan520.starwars.ui.people.detail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.winsontan520.starwars.R
import com.winsontan520.starwars.data.model.People
import com.winsontan520.starwars.ui.base.BaseActivity
import kotlinx.android.synthetic.main.appbar.*

class PeopleDetailActivity : BaseActivity() {

    companion object {
        const val INTENT_PEOPLE_ITEM: String = "INTENT_PEOPLE_ITEM"
    }

    private lateinit var viewModel : PeopleDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people_detail)

        viewModel = ViewModelProviders.of(this).get(PeopleDetailViewModel::class.java)

        viewModel.people = intent?.getParcelableExtra(INTENT_PEOPLE_ITEM)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_graph, PeopleDetailGraphFragment.newInstance())
                    .commitNow()

            supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_weight_listing, PeopleDetailListingFragment.newInstance())
                    .commitNow()
        }

        // set
        tv_title.text = viewModel.people?.name
    }
}