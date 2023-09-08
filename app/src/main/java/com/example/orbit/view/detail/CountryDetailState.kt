package com.example.orbit.view.detail

import com.example.orbit.view.detail.item.CountryDetailItem

data class CountryDetailState(
    val name: String,
    val isProgress: Boolean = false,
    val item: CountryDetailItem? = null,
    val error: Throwable? = null,
)