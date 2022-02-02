package com.s_h_y_a.kotlindatastore.pref

import androidx.datastore.preferences.core.*
import com.s_h_y_a.kotlindatastore.DataStorePrefDataConverter
import com.s_h_y_a.kotlindatastore.KotlinDataStoreModel
import com.s_h_y_a.kotlindatastore.SimpleDataStorePrefDataConverter

fun <V> KotlinDataStoreModel<V>.intFlowPref(
    default: Int = 0,
    key: String? = null
) = simpleFlowPref(default, key, ::intPreferencesKey)

fun <T, V> KotlinDataStoreModel<V>.saveAsIntFlowPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, Int>
) = dataStoreFlowPref(default, key, prefDataConverter, ::intPreferencesKey)


fun <V> KotlinDataStoreModel<V>.longFlowPref(
    default: Long = 0,
    key: String? = null
) = simpleFlowPref(default, key, ::longPreferencesKey)

fun <T, V> KotlinDataStoreModel<V>.saveAsLongFlowPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, Long>
) = dataStoreFlowPref(default, key, prefDataConverter, ::longPreferencesKey)


fun <V> KotlinDataStoreModel<V>.floatFlowPref(
    default: Float = 0f,
    key: String? = null
) = simpleFlowPref(default, key, ::floatPreferencesKey)

fun <T, V> KotlinDataStoreModel<V>.saveAsFloatFlowPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, Float>
) = dataStoreFlowPref(default, key, prefDataConverter, ::floatPreferencesKey)


fun <V> KotlinDataStoreModel<V>.doubleFlowPref(
    default: Double = 0.0,
    key: String? = null
) = simpleFlowPref(default, key, ::doublePreferencesKey)

fun <T, V> KotlinDataStoreModel<V>.saveAsDoubleFlowPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, Double>
) = dataStoreFlowPref(default, key, prefDataConverter, ::doublePreferencesKey)


fun <V> KotlinDataStoreModel<V>.booleanFlowPref(
    default: Boolean = false,
    key: String? = null
) = simpleFlowPref(default, key, ::booleanPreferencesKey)

fun <T, V> KotlinDataStoreModel<V>.saveAsBooleanFlowPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, Boolean>
) = dataStoreFlowPref(default, key, prefDataConverter, ::booleanPreferencesKey)


fun <V> KotlinDataStoreModel<V>.stringFlowPref(
    default: String = "",
    key: String? = null
) = simpleFlowPref(default, key, ::stringPreferencesKey)

fun <T, V> KotlinDataStoreModel<V>.saveAsStringFlowPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, String>
) = dataStoreFlowPref(default, key, prefDataConverter, ::stringPreferencesKey)


fun <T, V> KotlinDataStoreModel<V>.saveAsStringSetFlowPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, Set<String>>
) = dataStoreFlowPref(default, key, prefDataConverter, ::stringSetPreferencesKey)

fun <V> KotlinDataStoreModel<V>.stringSetFlowPref(
    default: Set<String> = emptySet(),
    key: String? = null
) = simpleFlowPref(default, key, ::stringSetPreferencesKey)

fun <T, V> KotlinDataStoreModel<V>.simpleFlowPref(
    default: T,
    key: String? = null,
    initializer: (preferenceKey: String) -> Preferences.Key<T>
): SimpleDataStoreFlowPref<T, V> = dataStoreFlowPref(
    default, key,
    SimpleDataStorePrefDataConverter(),
    initializer
)
