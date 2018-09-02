package com.winsontan520.starwars.ui.people.detail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.*
import com.github.mikephil.charting.data.Entry
import com.winsontan520.starwars.R
import com.winsontan520.starwars.ui.base.BaseFragment
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData
import kotlinx.android.synthetic.main.fragment_people_detail_graph.*

class PeopleDetailGraphFragment : BaseFragment() {

    companion object {
        fun newInstance() = PeopleDetailGraphFragment()
    }

    private lateinit var viewModel: PeopleDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity).get(PeopleDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_people_detail_graph, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeLiveData()

        generateChart()

    }

    private fun generateChart() {

        val chartEntries = ArrayList<Entry>()
        chartEntries.add(Entry(1.0f, 56.0f))
        chartEntries.add(Entry(2.0f, 57.0f))
        chartEntries.add(Entry(3.0f, 60.0f))
        chartEntries.add(Entry(4.0f, 68.0f))
        chartEntries.add(Entry(5.0f, 48.0f))

        val dataSet = LineDataSet(chartEntries, "Label")
        dataSet.valueTextColor = R.color.appbar_title

        val lineData = LineData(dataSet)
        chart.data = lineData
        chart.invalidate() // refresh

        //TODO
    }

    private fun observeLiveData() {

    }
}