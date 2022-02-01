package com.s_h_y_a.kotlindatastore.moshi

import com.s_h_y_a.kotlindatastore.DataStorePrefDataConverter
import com.squareup.moshi.JsonAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

fun <T> moshiDataStorePrefDataConverter(moshiAdapter: JsonAdapter<T>) =
    object : DataStorePrefDataConverter<T, String> {
        override suspend fun encode(value: T?) = withContext(Dispatchers.Default) {
            moshiAdapter.toJson(value)
        }

        override suspend fun decode(savedValue: String?, defaultValue: T) =
            withContext(Dispatchers.Default) {
                savedValue?.let(moshiAdapter::fromJson) ?: defaultValue
            }

    }