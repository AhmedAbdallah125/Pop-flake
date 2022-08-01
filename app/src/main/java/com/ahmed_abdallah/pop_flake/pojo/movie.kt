package com.ahmed_abdallah.pop_flake.pojo

import com.google.gson.annotations.SerializedName


data class MovieAPI(

    @SerializedName("items") var items: ArrayList<Movie> = arrayListOf()

)

data class Movie(

    @SerializedName("id") var id: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("fullTitle") var fullTitle: String? = null,
    @SerializedName("year") var year: String? = null,
    @SerializedName("releaseState") var releaseState: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("runtimeMins") var runtimeMins: String? = null,
    @SerializedName("runtimeStr") var runtimeStr: String? = null,
    @SerializedName("plot") var plot: String? = null,
    @SerializedName("contentRating") var contentRating: String? = null,
    @SerializedName("imDbRating") var imDbRating: String? = null,
    @SerializedName("imDbRatingCount") var imDbRatingCount: String? = null,
    @SerializedName("metacriticRating") var metacriticRating: String? = null,
    @SerializedName("genres") var genres: String? = null,
    @SerializedName("genreList") var genreList: ArrayList<String> = arrayListOf(),
    @SerializedName("directors") var directors: String? = null,
    @SerializedName("directorList") var directorList: ArrayList<String> = arrayListOf(),
    @SerializedName("stars") var stars: String? = null,
    @SerializedName("starList") var starList: ArrayList<String> = arrayListOf()

)