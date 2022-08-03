package com.ahmed_abdallah.pop_flake.pojo

import com.google.gson.annotations.SerializedName

data class TopRatedMovieAPI(

    @SerializedName("items") var items: List<TopRatedMovie> = arrayListOf(),

    )

data class TopRatedMovie    (

    @SerializedName("id") var id: String? = null,
    @SerializedName("rank") var rank: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("fullTitle") var fullTitle: String? = null,
    @SerializedName("year") var year: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("crew") var crew: String? = null,
    @SerializedName("imDbRating") var imDbRating: String? = null,
    @SerializedName("imDbRatingCount") var imDbRatingCount: String? = null

)