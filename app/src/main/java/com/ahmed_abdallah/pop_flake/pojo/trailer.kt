package com.ahmed_abdallah.pop_flake.pojo

import com.google.gson.annotations.SerializedName

data class Trailer (
    @SerializedName("imDbId"           ) var imDbId           : String? = null,
    @SerializedName("title"            ) var title            : String? = null,
    @SerializedName("fullTitle"        ) var fullTitle        : String? = null,
    @SerializedName("thumbnailUrl"     ) var thumbnailUrl     : String? = null,
    @SerializedName("link"             ) var link             : String? = null,
    @SerializedName("linkEmbed"        ) var linkEmbed        : String? = null,
)