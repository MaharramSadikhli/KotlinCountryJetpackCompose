package com.imsosoft.kotlincountryjetpackcompose.dependencyinjection

import com.imsosoft.kotlincountryjetpackcompose.repo.CountryRepo
import com.imsosoft.kotlincountryjetpackcompose.service.CountryAPI
import com.imsosoft.kotlincountryjetpackcompose.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCountryRepo(api: CountryAPI) = CountryRepo(api)


    @Provides
    @Singleton
    fun provideCountryAPI(): CountryAPI {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CountryAPI::class.java)
    }

}