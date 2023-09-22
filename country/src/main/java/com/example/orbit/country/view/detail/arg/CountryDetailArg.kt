package com.example.orbit.country.view.detail.arg

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CountryDetailArg(
    val code: String,
    val name: String,
) : Parcelable