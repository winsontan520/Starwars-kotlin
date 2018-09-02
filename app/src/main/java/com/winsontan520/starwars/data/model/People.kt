package com.winsontan520.starwars.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PeopleResponse(
        @SerializedName("count") val count: Int,
        @SerializedName("next") val next: String,
        @SerializedName("previous") val previous: Any?,
        @SerializedName("results") val results: MutableList<People>
)

@Parcelize
data class People(
        @SerializedName("name") val name: String,
        @SerializedName("height") val height: String,
        @SerializedName("mass") val mass: String,
        @SerializedName("hair_color") val hairColor: String,
        @SerializedName("skin_color") val skinColor: String,
        @SerializedName("eye_color") val eyeColor: String,
        @SerializedName("birth_year") val birthYear: String,
        @SerializedName("gender") val gender: String,
        @SerializedName("homeworld") val homeworld: String,
        @SerializedName("films") val films: MutableList<String>,
        @SerializedName("species") val species: MutableList<String>,
        @SerializedName("vehicles") val vehicles: MutableList<String>,
        @SerializedName("starships") val starships: MutableList<String>,
        @SerializedName("created") val created: String,
        @SerializedName("edited") val edited: String,
        @SerializedName("url") val url: String
) : Parcelable

data class PeopleWeight(
        val lastUpdate: String,
        val weight: String
)