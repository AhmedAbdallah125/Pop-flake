package com.ahmed_abdallah.pop_flake.pojo

import com.google.gson.annotations.SerializedName

data class PosterAPI (

    @SerializedName("imDbId"       ) var imDbId       : String?              = null,
    @SerializedName("title"        ) var title        : String?              = null,
    @SerializedName("posters"      ) var posters      : List<Posters>   = arrayListOf(),


)
data class Posters (
    @SerializedName("id"          ) var id          : String? = null,
    @SerializedName("link"        ) var link        : String? = null,
    @SerializedName("language"    ) var language    : String? = null,
    @SerializedName("width"       ) var width       : Int?    = null,
    @SerializedName("height"      ) var height      : Int?    = null

)