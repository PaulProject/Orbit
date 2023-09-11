package com.example.orbit.view.main

import androidx.lifecycle.ViewModel
import com.example.orbit.core.extension.createContainer
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val navigatorHolder: NavigatorHolder,
) : ViewModel(), ContainerHost<Any, Nothing> {

    override val container: Container<Any, Nothing> = createContainer(
        initialState = Any()
    )

    fun setNavigator(navigator: AppNavigator) {
        navigatorHolder.setNavigator(navigator)
    }

    fun removeNavigator() {
        navigatorHolder.removeNavigator()
    }

}