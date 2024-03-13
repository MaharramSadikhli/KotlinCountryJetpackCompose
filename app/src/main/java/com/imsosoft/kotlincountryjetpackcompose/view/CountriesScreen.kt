package com.imsosoft.kotlincountryjetpackcompose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.imsosoft.kotlincountryjetpackcompose.model.CountriesItem
import com.imsosoft.kotlincountryjetpackcompose.util.ExtensionFunc.countryParams
import com.imsosoft.kotlincountryjetpackcompose.viewmodel.CountriesViewModel

@Composable
fun CountriesScreen(
    navController: NavController,
    viewModel: CountriesViewModel = hiltViewModel()
) {
    Surface(
        color = MaterialTheme.colorScheme.tertiary,
        modifier = Modifier.fillMaxSize()
    )
    {
        Column {
            Text(text = "Countries",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Green
            )
            Spacer(modifier = Modifier.height(10.dp))
            SearchBar(
                hint = "Search...",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                viewModel.searchCountries(it)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Countries(navController = navController)
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    var text by remember { mutableStateOf("") }
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .onFocusChanged {
                    isHintDisplayed = it.isFocused != true && text.isEmpty()
                }
            )

        if (isHintDisplayed) {
            Text(text = hint,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, vertical = 12.dp))
        }
    }
}

@Composable
fun Countries(navController: NavController, viewModel: CountriesViewModel = hiltViewModel()) {
    val countries by remember { viewModel.countries }
    val error by remember { viewModel.error }
    val isLoading by remember { viewModel.isLoading }

    CountriesView(countries = countries, navController = navController)

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (isLoading) {
            CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
        }

        if (error.isNotEmpty()) {
            RetryView(error = error) {
                viewModel.loadCountries()
            }
        }
    }

}

@Composable
fun CountriesView(countries: List<CountriesItem>, navController: NavController) {
    LazyColumn(contentPadding = PaddingValues(5.dp))
    {
        items(countries) { country ->
            CountriesItemRow(country = country, navController = navController)
        }
    }
}

@Composable
fun CountriesItemRow(country: CountriesItem, navController: NavController) {
    Box {}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.secondary)
            .clickable {
                navController.navigate(
                    route = country
                        .countryParams(
                            country.name,
                            country.capital,
                            country.region,
                            country.currency,
                            country.flag,
                            country.language
                        )
                        .toString()
                    //"country_detail_screen/${country.name}/${country.capital}/${country.region}/${country.currency}/${country.flag}/${country.language}"
                )
            }
    )
    {
        CountriesNameText(text = country.name)
        CountriesText(text = country.capital)
        CountriesText(text = country.currency)
    }
}



@Composable
fun RetryView(error: String, onRetry: () -> Unit) {
    Column {
        Text(text = error, color = Color.Red, fontSize = 32.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = { onRetry() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = "Retry")
        }
    }
}