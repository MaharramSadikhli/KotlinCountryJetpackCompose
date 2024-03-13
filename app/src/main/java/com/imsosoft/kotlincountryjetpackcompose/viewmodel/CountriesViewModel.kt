package com.imsosoft.kotlincountryjetpackcompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imsosoft.kotlincountryjetpackcompose.model.CountriesItem
import com.imsosoft.kotlincountryjetpackcompose.repo.CountryRepo
import com.imsosoft.kotlincountryjetpackcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val repo: CountryRepo): ViewModel()
{

    private var countries = mutableStateOf<List<CountriesItem>>(listOf()) // mutableStateOf<Countries>(listOf())
    private var error = mutableStateOf("")
    private var isLoading = mutableStateOf(false)

    private var initCountries = listOf<CountriesItem>()
    private var isSearchStarting = true

    init {
    }



}