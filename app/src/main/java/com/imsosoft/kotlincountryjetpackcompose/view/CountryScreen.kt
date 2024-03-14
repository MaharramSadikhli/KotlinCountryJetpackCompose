package com.imsosoft.kotlincountryjetpackcompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.imsosoft.kotlincountryjetpackcompose.model.Country
import com.imsosoft.kotlincountryjetpackcompose.util.Resource
import com.imsosoft.kotlincountryjetpackcompose.viewmodel.CountryViewModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CountryScreen(
    id: String,
    name: String,
    capital: String,
    region: String,
    currency: String,
    //flag: String,
    language: String,
    navController: NavController,
    viewModel: CountryViewModel = hiltViewModel()
)
{

    val countryItem = produceState<Resource<Country>>(initialValue = Resource.Loading()) {
        value = viewModel.getCountry(id)
    }.value


    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.secondary),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            when(countryItem) {
                is Resource.Success -> {
                    val selectedCountry = countryItem.data!![0]

                    // or get image data from countriesItem flag
                    Image(
                        painter = rememberImagePainter(data = selectedCountry.flagUrl),
                        contentDescription = selectedCountry.name,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(200.dp, 200.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color.Gray, CircleShape)
                    )

                    CountriesNameText(text = name)
                    CountriesText(text = capital)
                    CountriesText(text = language)
                    CountriesText(text = currency)
                    CountriesText(text = region)
                }
                is Resource.Error -> {
                    Text(text = countryItem.message!!)
                }
                is Resource.Loading -> {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            }
        }
    }

}