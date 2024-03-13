package com.imsosoft.kotlincountryjetpackcompose.model

import com.google.gson.annotations.SerializedName

data class CountryItem(
    val id: String,
    @SerializedName(value = "flag_url")
    val flagUrl: String,
    val name: String
)