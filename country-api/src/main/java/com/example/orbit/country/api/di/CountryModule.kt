package com.example.orbit.country.api.di

import com.example.orbit.country.api.navigation.CountryNavigator
import com.example.orbit.country.api.navigation.ICountryNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal interface CountryModule {

    @Singleton
    @Binds
    fun bindCountryNavigator(impl: CountryNavigator): ICountryNavigator

}