package com.example.orbit.core.coroutine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler

inline fun CoroutineExceptionHandler(crossinline handler: (Throwable) -> Unit): CoroutineExceptionHandler =
    CoroutineExceptionHandler { _, throwable ->
        if (throwable !is CancellationException) handler.invoke(throwable)
    }