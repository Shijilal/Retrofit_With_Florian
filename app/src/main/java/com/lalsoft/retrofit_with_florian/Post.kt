package com.lalsoft.retrofit_with_florian

import com.google.gson.annotations.SerializedName

class Post(
    val userId: Int,
    val title: String? = null,
    @SerializedName("body")
    val text: String
) {
    val id: Int? = null

}

class PostPatch(
    val userId: Int,
    @SerializedName("body")
    val text: String
)

