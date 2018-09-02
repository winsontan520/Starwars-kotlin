package com.winsontan520.starwars.ui.people.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.winsontan520.starwars.data.model.People
import com.winsontan520.starwars.data.model.PeopleWeight
import com.winsontan520.starwars.util.DateUtil

class PeopleDetailViewModel : ViewModel() {

    val weightListingLiveData: MutableLiveData<PeopleWeight>

    init {
        weightListingLiveData = MutableLiveData()
    }

    var people: People? = null

    fun addWeight(weight: PeopleWeight) {
        weightListingLiveData.value = weight
    }

    fun addWeight(weight: Double) {
        addWeight(PeopleWeight(weight = weight.toString(), lastUpdate = DateUtil.getCurrentDateString()))
    }

    fun displayDefaultWeight() {
        people?.let {
            addWeight(PeopleWeight(lastUpdate = it.edited, weight = it.mass))
        }
    }

}