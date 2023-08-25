package com.example.orbit.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.orbit.R
import com.example.orbit.ui.theme.OrbitTheme
import com.example.orbit.view.item.CountryItem
import dagger.hilt.android.AndroidEntryPoint
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class CountryListFragment : Fragment(R.layout.activity_main) {

    private val viewModel: CountryListViewModel by viewModels()

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View = ComposeView(requireContext()).apply {
//        setContent {
//            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
//            OrbitTheme {
//                CountryListScreen(viewModel)
//            }
//        }
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.observe(
            lifecycleOwner = this,
            state = { state ->
                handleProgress(state.isProgress)
                handleItems(state.items)
            },
            sideEffect = { effect ->
                when (effect) {
                    is CountryListSideEffect.ShowNameOfCountryDialog -> showDialog()
                }
            }
        )
    }

    private fun handleProgress(progress: Boolean) {
        TODO("Not yet implemented")
    }

    private fun handleItems(items: List<CountryItem>) {
        binding.rv.adapter.items = items
    }

}