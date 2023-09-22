package com.example.orbit.view.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.FragmentActivity
import com.example.orbit.R
import com.example.orbit.country.api.navigation.ICountryNavigator
import com.github.terrakok.cicerone.Command
import com.github.terrakok.cicerone.Replace
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : FragmentActivity(R.layout.activity_main) {

    @Inject
    lateinit var countryNavigator: ICountryNavigator

    private val viewModel: MainViewModel by viewModels()
    private val navigator = AppNavigator(this, R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            navigator.applyCommands(
                arrayOf<Command>(
                    Replace(countryNavigator.getCountryListScreen())
                )
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.setNavigator(navigator)
    }

    override fun onPause() {
        viewModel.removeNavigator()
        super.onPause()
    }

}