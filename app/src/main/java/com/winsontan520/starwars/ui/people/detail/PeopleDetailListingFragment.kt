package com.winsontan520.starwars.ui.people.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.winsontan520.starwars.R
import com.winsontan520.starwars.data.model.PeopleWeight
import com.winsontan520.starwars.ui.base.BaseFragment
import com.winsontan520.starwars.util.hideKeyboard
import kotlinx.android.synthetic.main.fragment_people_detail_listing.*

class PeopleDetailListingFragment : BaseFragment() {

    companion object {
        fun newInstance() = PeopleDetailListingFragment()
    }

    private lateinit var viewModel: PeopleDetailViewModel
    private var adapter = PeopleWeighListingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(PeopleDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_people_detail_listing, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_view.layoutManager = LinearLayoutManager(activity)
        recycler_view.adapter = adapter

        observeLiveData()

        if (savedInstanceState == null) {
            viewModel.displayDefaultWeight()
        }

        tv_add.setOnClickListener {
            addNewWeight()
        }
    }

    override fun onResume() {
        super.onResume()
        activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    private fun addNewWeight() {
        if (et_new_weight.text.toString().isNotEmpty()) {
            val newWeight: Double = et_new_weight.text.toString().toDouble()
            if (newWeight > 0.0) {
                viewModel.addWeight(newWeight)
            }
        }
    }

    private fun observeLiveData() {
        viewModel.weightListingLiveData.observe(this, weightListingObserver)
    }

    // Create the observer which updates the UI.
    private val weightListingObserver: Observer<PeopleWeight> = Observer {
        it?.let {
            hideKeyboard(recycler_view)
            adapter.addWeight(it)
            recycler_view.scrollToPosition(0)
        }
    }
}