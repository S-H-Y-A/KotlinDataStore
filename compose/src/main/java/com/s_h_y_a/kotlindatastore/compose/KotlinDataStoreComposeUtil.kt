package com.s_h_y_a.kotlindatastore.compose

import androidx.compose.runtime.*
import com.s_h_y_a.kotlindatastore.DataStoreFlow
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Composable
fun <T, R, V> DataStoreFlow<T,R,V>.collectAsMutableState(context: CoroutineContext = EmptyCoroutineContext, scope: CoroutineScope = rememberCoroutineScope()): MutableState<T> {
    val state by collectAsState(defaultValue, context)
    return DataStoreState(scope, this, state)
}

private class DataStoreState<T, R, V> (private val scope: CoroutineScope, private val dataStoreFlow: DataStoreFlow<T,R,V>, private val state: T): MutableState<T> {
    override var value: T
        get() = state
        set(value) = dataStoreFlow.emitIn(scope, value)

    override operator fun component1(): T = value

    override operator fun component2(): (T) -> Unit = { value = it }
}