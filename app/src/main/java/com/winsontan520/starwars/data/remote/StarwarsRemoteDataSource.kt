package com.winsontan520.starwars.data.remote

import com.winsontan520.starwars.data.model.People
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

object StarwarsRemoteDataSource : StarwarsDataSource {

    override fun getPeople(page: Int): Single<MutableList<People>> =
            StarwarsApi.create("https://swapi.co/").getPeople(page).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map { it.results }

}