package com.s_h_y_a.kotlindatastore.gson

import com.google.gson.Gson
import com.s_h_y_a.kotlindatastore.DataStorePrefDataConverter
import com.s_h_y_a.kotlindatastore.KotlinDataStoreModel
import com.s_h_y_a.kotlindatastore.KotlinDataStoreModelWithScope
import com.s_h_y_a.kotlindatastore.pref.saveAsStringFlowPref
import com.s_h_y_a.kotlindatastore.pref.saveAsStringPref
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.reflect.Type

inline fun <reified T, V> KotlinDataStoreModelWithScope<V>.gsonPref(
    default: T,
    key: String? = null,
    gson: Gson = Gson()
) = saveAsStringPref(
    default,
    key,
    object : DataStorePrefDataConverter<T, String> {
        override suspend fun encode(value: T?) = withContext(Dispatchers.Default) {
            gson.toJson(value)
        }

        override suspend fun decode(savedValue: String?, defaultValue: T) = withContext(Dispatchers.Default) {
            gson.fromJson(savedValue, T::class.java) ?: defaultValue
        }

    }
)

fun <T, V> KotlinDataStoreModelWithScope<V>.gsonTypePref(
    type: Type,
    default: T,
    key: String? = null,
    gson: Gson = Gson()
) = saveAsStringPref(
    default,
    key,
    object : DataStorePrefDataConverter<T, String> {
        override suspend fun encode(value: T?) = withContext(Dispatchers.Default) {
            gson.toJson(value)
        }

        override suspend fun decode(savedValue: String?, defaultValue: T) = withContext(Dispatchers.Default) {
            gson.fromJson<T>(savedValue, type) ?: defaultValue
        }

    }
)

// ======================================================

inline fun <reified T, V> KotlinDataStoreModel<V>.gsonFlowPref(
    default: T,
    key: String? = null,
    gson: Gson = Gson()
) = saveAsStringFlowPref(
    default,
    key,
    object : DataStorePrefDataConverter<T, String> {
        override suspend fun encode(value: T?) = withContext(Dispatchers.Default) {
            gson.toJson(value)
        }

        override suspend fun decode(savedValue: String?, defaultValue: T) = withContext(Dispatchers.Default) {
            gson.fromJson(savedValue, T::class.java) ?: defaultValue
        }

    }
)

fun <T, V> KotlinDataStoreModel<V>.gsonTypeFlowPref(
    type: Type,
    default: T,
    key: String? = null,
    gson: Gson = Gson()
) = saveAsStringFlowPref(
    default,
    key,
    object : DataStorePrefDataConverter<T, String> {
        override suspend fun encode(value: T?) = withContext(Dispatchers.Default) {
            gson.toJson(value)
        }

        override suspend fun decode(savedValue: String?, defaultValue: T) = withContext(Dispatchers.Default) {
            gson.fromJson<T>(savedValue, type) ?: defaultValue
        }

    }
)
