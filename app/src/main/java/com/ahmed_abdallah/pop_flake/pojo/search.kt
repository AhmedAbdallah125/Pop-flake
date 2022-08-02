package com.ahmed_abdallah.pop_flake.pojo

import com.google.gson.annotations.SerializedName

data class SearchResultAPI(

    @SerializedName("searchType") var searchType: String? = null,
    @SerializedName("expression") var expression: String? = null,
    @SerializedName("results") var results: ArrayList<SearchResult> = arrayListOf(),
    @SerializedName("errorMessage") var errorMessage: String? = null

)

data class SearchResult(

    @SerializedName("id") var id: String? = null,
    @SerializedName("resultType") var resultType: String? = null,
    @SerializedName("image") var image: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("description") var description: String? = null

)