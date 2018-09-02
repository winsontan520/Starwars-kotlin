package com.winsontan520.starwars.ui.people.listing

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.winsontan520.starwars.data.model.People

class PeopleListingViewModel : ViewModel() {

    private val pageLiveData = MutableLiveData<Int>()

    val resultLiveData = PeopleListingLiveData().apply {
        this.addSource(pageLiveData) {
            it?.let { result -> this.page = it }
        }
    }

    val isLoadingLiveData = MediatorLiveData<Boolean>().apply {
        this.addSource(resultLiveData) { this.value = false }
    }

    val throwableLiveData = MediatorLiveData<Throwable>().apply {
        this.addSource(resultLiveData) { it?.second?.let { this.value = it } }
    }

    val peopleListingLiveData = MediatorLiveData<MutableList<People>>().apply {
        this.addSource(resultLiveData) { it?.first?.let { this.value = it } }
    }

    fun refresh() {
        pageLiveData.value = 1
        isLoadingLiveData.value = true
    }

    fun loadMore() {
        pageLiveData.value?.plus(1)
    }

}