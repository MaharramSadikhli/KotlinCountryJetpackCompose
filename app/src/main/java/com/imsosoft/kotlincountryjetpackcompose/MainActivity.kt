package com.imsosoft.kotlincountryjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.imsosoft.kotlincountryjetpackcompose.model.CountriesItem
import com.imsosoft.kotlincountryjetpackcompose.ui.theme.KotlinCountryJetpackComposeTheme
import com.imsosoft.kotlincountryjetpackcompose.ui.theme.Typography
import com.imsosoft.kotlincountryjetpackcompose.util.ExtensionFunc
import com.imsosoft.kotlincountryjetpackcompose.view.CountriesScreen
import com.imsosoft.kotlincountryjetpackcompose.view.CountryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KotlinCountryJetpackComposeTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "countries_screen")
                {
                    composable("countries_screen") {
                        CountriesScreen(navController = navController)
                    }

                    composable(
                        "country_screen/{countryId}/{countryName}/{countryCapital}/{countryRegion}/{countryCurrency}/{countryLanguage}",
                        arguments = listOf(
                            navArgument("countryId") {
                                type = NavType.StringType
                            },
                            navArgument("countryName") {
                                type = NavType.StringType
                            },
                            navArgument("countryCapital") {
                                type = NavType.StringType
                            },
                            navArgument("countryRegion") {
                                type = NavType.StringType
                            },
                            navArgument("countryCurrency") {
                                type = NavType.StringType
                            },
                            navArgument("countryLanguage") {
                                type = NavType.StringType
                            }
                        )
                    ) {
                        val countryId = remember {
                            it.arguments?.getString("countryId")
                        }
                        val countryName = remember {
                            it.arguments?.getString("countryName")
                        }
                        val countryCapital = remember {
                            it.arguments?.getString("countryCapital")
                        }
                        val countryRegion = remember {
                            it.arguments?.getString("countryRegion")
                        }
                        val countryCurrency = remember {
                            it.arguments?.getString("countryCurrency")
                        }
                        val countryLanguage = remember {
                            it.arguments?.getString("countryLanguage")
                        }
                        
                        CountryScreen(
                            id = countryId ?: "",
                            name = countryName ?: "",
                            capital = countryCapital ?: "",
                            region = countryRegion ?: "",
                            currency = countryCurrency ?: "",
                            language = countryLanguage ?: "",
                            navController = navController
                        )

                    }

                }
            }
        }
    }
}

