package com.imsosoft.kotlincountryjetpackcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.imsosoft.kotlincountryjetpackcompose.model.Country
import com.imsosoft.kotlincountryjetpackcompose.repo.CountryRepo
import com.imsosoft.kotlincountryjetpackcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val repo: CountryRepo): ViewModel()
{
    suspend fun getCountry(id: String): Resource<Country> {
        return repo.getCountry(id)
    }
}