package com.imsosoft.kotlincountryjetpackcompose.service

import com.imsosoft.kotlincountryjetpackcompose.model.Countries
import com.imsosoft.kotlincountryjetpackcompose.model.Country
import com.imsosoft.kotlincountryjetpackcompose.util.Constants
import retrofit2.http.GET

interface CountryAPI {

    @GET(Constants.END_POINT_COUNTRIES)
    suspend fun getCountries(): Countries

    @GET(Constants.END_POINT_FLAGS)
    suspend fun getCountry(): Country

}