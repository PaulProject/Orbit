package com.example.orbit.country.api.navigation

import com.example.orbit.country.navigation.CountryStackScreen
import com.github.terrakok.cicerone.androidx.FragmentScreen
import javax.inject.Inject

interface ICountryNavigator {

    fun getCountryListScreen(): FragmentScreen

}

internal class CountryNavigator @Inject constructor() : ICountryNavigator {

    override fun getCountryListScreen(): FragmentScreen =
        CountryStackScreen.CountryListScreen()

}