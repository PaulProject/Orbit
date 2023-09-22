package com.example.orbit.country.view.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.orbit.country.view.detail.arg.CountryDetailArg
import com.example.orbit.design.theme.OrbitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryDetailFragment : Fragment() {

    companion object {
        fun newInstance(arg: CountryDetailArg): Fragment =
            CountryDetailFragment().apply {
                arguments = bundleOf(CountryDetailViewModel.ARG_COUNTRY_DETAIL to arg)
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            OrbitTheme {
                CountryDetailScreen()
            }
        }
    }

}