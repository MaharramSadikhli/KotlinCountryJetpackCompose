package com.imsosoft.kotlincountryjetpackcompose.util

import com.imsosoft.kotlincountryjetpackcompose.model.CountriesItem

object ExtensionFunc {

    fun CountriesItem.countryParams(name: String, capital: String, region: String, currency: String, flag: String, language: String): CountriesItem {
        return "country_screen/${name}/$capital/$region/$currency/$flag/$language".toCountriesItem()
    }

    private fun String.toCountriesItem(): CountriesItem {
        return CountriesItem(
            name = this.split("/")[1],
            capital = this.split("/")[2],
            region = this.split("/")[3],
            currency = this.split("/")[4],
            flag = this.split("/")[5],
            language = this.split("/")[6])
    }
}