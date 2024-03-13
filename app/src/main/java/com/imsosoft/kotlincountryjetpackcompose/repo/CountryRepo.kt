package com.imsosoft.kotlincountryjetpackcompose.repo

import com.imsosoft.kotlincountryjetpackcompose.model.Countries
import com.imsosoft.kotlincountryjetpackcompose.model.Country
import com.imsosoft.kotlincountryjetpackcompose.service.CountryAPI
import com.imsosoft.kotlincountryjetpackcompose.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class CountryRepo @Inject constructor(
    private val api: CountryAPI
) {
    suspend fun getCountries(): Resource<Countries> {
        val response = try {
            api.getCountries()
        } catch (e: Exception) {
            return Resource.Error("Error!")
        }

        return Resource.Success(response)
    }

    suspend fun getCountry(id: String): Resource<Country> {
        val response = try {
            api.getCountry()
        } catch (e: Exception) {
            return Resource.Error("Error!")
        }

        return Resource.Success(response)
    }
}