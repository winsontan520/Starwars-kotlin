package com.winsontan520.starwars.data.remote

import com.winsontan520.starwars.data.model.PeopleResponse
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface StarwarsApi {
    companion object {
        fun create(baseUrl: String): StarwarsApi {
            var retrofitBuilder = Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
            val retrofit = retrofitBuilder.build()
            return retrofit.create(StarwarsApi::class.java)
        }
    }

    @GET("api/people")
    fun getPeople(@Query("page") page: Int): Single<PeopleResponse>

}