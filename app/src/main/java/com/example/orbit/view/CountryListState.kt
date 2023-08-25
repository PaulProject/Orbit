package com.example.orbit.view

import com.example.orbit.view.item.CountryItem

data class CountryListState(
    val isProgress: Boolean = false,
    val error: Throwable? = null,
    val items: List<CountryItem> = emptyList(),
)