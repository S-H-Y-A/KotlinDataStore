@file:Suppress("unused")

package com.s_h_y_a.kotlindatastore

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

typealias SimpleDataStoreFlow<T, V> = DataStoreFlow<T, T, V>

class DataStoreFlow<T, R, V> internal constructor(
    data: Flow<T>,
    private val converterWrapper: DataStorePrefDataConverterWrapper<T,R>,
    val id: Preferences.Key<R>
): Flow<T> by data {
    val defaultValue = converterWrapper.defaultValue

    suspend fun emit(value: T) = converterWrapper.save(value)

    fun emitIn(scope: CoroutineScope, value: T) {
        scope.launch {
            emit(value)
        }
    }

    internal suspend fun encode(value: T) = converterWrapper.encode(value)
}