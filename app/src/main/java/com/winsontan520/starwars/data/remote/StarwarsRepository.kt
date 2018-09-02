package com.winsontan520.starwars.data.remote

import com.winsontan520.starwars.data.model.People
import io.reactivex.Single

object StarwarsRepository : StarwarsDataSource {
    override fun getPeople(page: Int): Single<MutableList<People>> {
        return StarwarsRemoteDataSource.getPeople(page)
    }
}