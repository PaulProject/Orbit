package com.example.orbit.core.extension

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

sealed interface RequestAction<out T> {

    data class Progress(val progress: Boolean) : RequestAction<Nothing>
    data class Data<T>(val data: T) : RequestAction<T>
    data class Error(val error: Throwable) : RequestAction<Nothing>
}

fun <T> request(request: suspend () -> T): Flow<RequestAction<T>> =
    flow { emit(request()) }
        .map<T, RequestAction<T>> { RequestAction.Data(it) }
        .onStart { emit(RequestAction.Progress(true)) }
        .catch { emit(RequestAction.Error(it)) }
        .onCompletion { emit(RequestAction.Progress(false)) }