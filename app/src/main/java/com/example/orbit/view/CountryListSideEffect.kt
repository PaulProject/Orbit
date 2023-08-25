package com.example.orbit.view

sealed interface CountryListSideEffect {
    data class ShowNameOfCountryDialog(val name: String): CountryListSideEffect

}