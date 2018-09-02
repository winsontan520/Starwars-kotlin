package com.winsontan520.starwars.ui.people.listing

import android.arch.lifecycle.MediatorLiveData
import com.winsontan520.starwars.data.model.People
import com.winsontan520.starwars.data.remote.StarwarsRepository
import io.reactivex.disposables.Disposable

class PeopleListingLiveData : MediatorLiveData<Pair<MutableList<People>?, Throwable?>>() {
    private var disposable: Disposable? = null

    var page: Int? = null
        set(value) {
            value?.let {
                disposable = StarwarsRepository.getPeople(it).subscribe { data, error ->
                    this@PeopleListingLiveData.value = Pair(data, error)
                }
            }
        }

    override fun onInactive() {
        super.onInactive()
        if (disposable?.isDisposed?.not() ?: false) {
            disposable?.dispose()
        }
    }
}