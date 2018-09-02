package com.winsontan520.starwars.data.remote

import com.winsontan520.starwars.data.model.People
import io.reactivex.Single

interface StarwarsDataSource {
    fun getPeople(page: Int): Single<MutableList<People>>
}