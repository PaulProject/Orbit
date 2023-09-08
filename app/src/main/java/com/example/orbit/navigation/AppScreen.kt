package com.example.orbit.navigation

import com.example.orbit.view.detail.CountryDetailFragment
import com.example.orbit.view.detail.arg.CountryDetailArg
import com.example.orbit.view.list.CountryListFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object AppScreen {

    fun CountryListFragment(): FragmentScreen =
        FragmentScreen { CountryListFragment.newInstance() }

    fun CountryDetailFragment(code: String, name: String): FragmentScreen =
        FragmentScreen {
            CountryDetailFragment.newInstance(
                CountryDetailArg(code = code, name = name)
            )
        }

}