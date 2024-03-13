package com.imsosoft.kotlincountryjetpackcompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imsosoft.kotlincountryjetpackcompose.model.CountriesItem
import com.imsosoft.kotlincountryjetpackcompose.repo.CountryRepo
import com.imsosoft.kotlincountryjetpackcompose.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountriesViewModel @Inject constructor(private val repo: CountryRepo): ViewModel()
{

    var countries = mutableStateOf<List<CountriesItem>>(listOf()) // mutableStateOf<Countries>(listOf())
    var error = mutableStateOf("")
    var isLoading = mutableStateOf(false)

    private var initCountries = listOf<CountriesItem>()
    private var isSearchStarting = true

    init {
        loadCountries()
    }

    fun searchCountries(query: String) {
        val listToSearch =
            if (isSearchStarting) countries.value
            else initCountries

        viewModelScope.launch(Dispatchers.Default) {
            if (query.isEmpty()) {
                countries.value = initCountries
                isSearchStarting = true
                return@launch
            }

            val results = listToSearch.filter {
                it.name.contains(query.trim(), ignoreCase = true)
            }

            if (isSearchStarting) {
                initCountries = countries.value
                isSearchStarting = false
            }

            countries.value = results
        }

    }

    fun loadCountries() {

        viewModelScope.launch {
            isLoading.value = true
            when (val result = repo.getCountries()) {

                is Resource.Success -> {
                    val countriesItem = result.data!!.mapIndexed { index, item ->
                        CountriesItem(
                            item.name,
                            item.capital,
                            item.region,
                            item.currency,
                            item.flag,
                            item.language,
                        )
                    }

                    error.value = ""
                    isLoading.value = false
                    countries.value += countriesItem
                }

                is Resource.Error -> {
                    error.value = result.message!!
                    isLoading.value = false
                }

                is Resource.Loading -> {
                    error.value = ""
                }

            }

        }

    }

}