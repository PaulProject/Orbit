package com.example.orbit.country.navigation

import com.example.orbit.country.view.detail.CountryDetailFragment
import com.example.orbit.country.view.detail.arg.CountryDetailArg
import com.example.orbit.country.view.list.CountryListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object CountryStackScreen {

    fun CountryListScreen(): FragmentScreen =
        FragmentScreen { CountryListFragment.newInstance() }

    fun CountryDetailScreen(code: String, name: String): FragmentScreen =
        FragmentScreen {
            CountryDetailFragment.newInstance(
                CountryDetailArg(code = code, name = name)
            )
        }

}