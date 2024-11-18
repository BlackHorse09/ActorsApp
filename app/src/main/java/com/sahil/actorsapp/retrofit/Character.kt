package com.sahil.actorsapp.retrofit

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Character(
    val actor: String,
    val image: String
)
