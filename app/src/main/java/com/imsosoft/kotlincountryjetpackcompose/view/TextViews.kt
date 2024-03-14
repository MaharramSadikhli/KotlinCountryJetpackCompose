package com.imsosoft.kotlincountryjetpackcompose.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CountriesText(text: String) {

    Text(
        text = text,
        style = MaterialTheme.typography.displayMedium,
        modifier = Modifier.padding(2.dp),
        color = MaterialTheme.colorScheme.primary
    )

}

@Composable
fun CountriesNameText(text: String) {

    Text(
        text = text,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.padding(2.dp),
        color = MaterialTheme.colorScheme.primary
    )

}