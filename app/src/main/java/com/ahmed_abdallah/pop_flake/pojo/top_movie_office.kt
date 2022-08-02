package com.ahmed_abdallah.pop_flake.pojo

import com.google.gson.annotations.SerializedName

data class TopMoviesOfficeAPI(

    @SerializedName("items") var items: ArrayList<BoxOfficeMovie> = arrayListOf()

)
data class BoxOfficeMovie (

    @SerializedName("id"      ) var id      : String? = null,
    @SerializedName("rank"    ) var rank    : String? = null,
    @SerializedName("title"   ) var title   : String? = null,
    @SerializedName("image"   ) var image   : String? = null,
    @SerializedName("weekend" ) var weekend : String? = null,
    @SerializedName("gross"   ) var gross   : String? = null,
    @SerializedName("weeks"   ) var weeks   : String? = null

)