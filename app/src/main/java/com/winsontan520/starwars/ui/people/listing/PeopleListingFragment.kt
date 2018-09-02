package com.winsontan520.starwars.ui.people.listing

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.winsontan520.starwars.R
import com.winsontan520.starwars.data.model.People
import com.winsontan520.starwars.ui.base.BaseFragment
import com.winsontan520.starwars.ui.people.detail.PeopleDetailActivity
import kotlinx.android.synthetic.main.fragment_people_listing.*

class PeopleListingFragment : BaseFragment() {

    companion object {
        fun newInstance() = PeopleListingFragment()
    }

    private lateinit var viewModel: PeopleListingViewModel
    private var adapter = PeopleListingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PeopleListingViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_people_listing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.setOnItemClickListener(object : PeopleListingAdapter.OnItemClickListener {
            override fun onItemClick(item: People) {
                openPeopleDetailPage(item)
            }
        })

        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.adapter = adapter

        swipe_layout.setOnRefreshListener { viewModel.refresh() }

        if (savedInstanceState == null) {
            viewModel.refresh()
        }

        observeLiveData()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


    }

    private fun observeLiveData() {
        viewModel.isLoadingLiveData.observe(this, Observer<Boolean> {
            it?.let { swipe_layout.isRefreshing = it }
        })
        viewModel.peopleListingLiveData.observe(this, Observer<MutableList<People>> { it ->
            it?.let {
                adapter.dataSource = it
            }
        })

        viewModel.throwableLiveData.observe(this, Observer<Throwable> {
            it?.let { Snackbar.make(recycler_view, it.localizedMessage, Snackbar.LENGTH_LONG).show() }
        })
    }

    private fun openPeopleDetailPage(item: People) {
        val intent = Intent(activity, PeopleDetailActivity::class.java)
        intent.putExtra(PeopleDetailActivity.INTENT_PEOPLE_ITEM, item)
        startActivity(intent)
    }
}