package com.example.orbit.view.list

import com.example.orbit.view.list.item.CountryListItem

data class CountryListState(
    val isProgress: Boolean = false,
    val error: Throwable? = null,
    val items: List<CountryListItem> = emptyList(),
)