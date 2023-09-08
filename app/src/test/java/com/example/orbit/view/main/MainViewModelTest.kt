package com.example.orbit.view.main

import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.androidx.AppNavigator
import io.mockk.MockKAnnotations
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import io.mockk.justRun
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @MockK
    lateinit var navigatorHolder: NavigatorHolder

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(
            navigatorHolder = navigatorHolder
        )
    }

    @After
    fun tearDown() {
        confirmVerified(
            navigatorHolder
        )
    }

    @Test
    fun setNavigator() {
        // mock
        val navigator = mockk<AppNavigator>()
        justRun { navigatorHolder.setNavigator(navigator) }

        // action
        viewModel.setNavigator(navigator)

        // verify
        verify { navigatorHolder.setNavigator(navigator) }
    }

    @Test
    fun removeNavigator() {
        // mock
        justRun { navigatorHolder.removeNavigator() }

        // action
        viewModel.removeNavigator()

        // verify
        verify { navigatorHolder.removeNavigator() }
    }
}