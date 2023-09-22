package com.example.orbit.country.view.list

sealed interface CountryListSideEffect {
    data class ShowNameOfCountryDialog(val name: String): CountryListSideEffect

}