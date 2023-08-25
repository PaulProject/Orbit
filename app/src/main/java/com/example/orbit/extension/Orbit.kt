package com.example.orbit.extension

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.orbit.coroutine.CoroutineExceptionHandler
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.viewmodel.container

fun <STATE : Any, SIDE_EFFECT : Any> ViewModel.createContainer(
    initialState: STATE,
    errorHandler: (Throwable) -> Unit = {
        Log.e("AppError", Log.getStackTraceString(it))
    },
    onCreate: (suspend SimpleSyntax<STATE, SIDE_EFFECT>.() -> Unit)? = null
): Container<STATE, SIDE_EFFECT> {
    return container(
        initialState = initialState,
        buildSettings = {
            exceptionHandler = CoroutineExceptionHandler(errorHandler)
        },
        onCreate = onCreate,
    )
}