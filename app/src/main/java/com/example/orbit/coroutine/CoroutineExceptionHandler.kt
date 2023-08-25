package com.example.orbit.coroutine

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler

inline fun CoroutineExceptionHandler(crossinline handler: (Throwable) -> Unit): CoroutineExceptionHandler =
    CoroutineExceptionHandler { _, throwable ->
        if (throwable !is CancellationException) handler.invoke(throwable)
    }