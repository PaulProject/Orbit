package com.example.orbit.view.list

sealed interface CountryListSideEffect {
    data class ShowNameOfCountryDialog(val name: String): CountryListSideEffect

}