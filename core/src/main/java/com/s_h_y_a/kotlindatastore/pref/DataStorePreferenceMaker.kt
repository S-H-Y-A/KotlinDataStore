package com.s_h_y_a.kotlindatastore.pref

import androidx.datastore.preferences.core.*
import com.s_h_y_a.kotlindatastore.DataStorePrefDataConverter
import com.s_h_y_a.kotlindatastore.KotlinDataStoreModel
import com.s_h_y_a.kotlindatastore.KotlinDataStoreModelWithScope
import com.s_h_y_a.kotlindatastore.SimpleDataStorePrefDataConverter

fun <V> KotlinDataStoreModelWithScope<V>.intPref(
    default: Int = 0,
    key: String? = null
) = simplePref(default, key, ::intPreferencesKey)

fun <T, V> KotlinDataStoreModelWithScope<V>.saveAsIntPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, Int>
) = dataStorePref(default, key, prefDataConverter, ::intPreferencesKey)


fun <V> KotlinDataStoreModelWithScope<V>.doublePref(
    default: Double = 0.0,
    key: String? = null
) = simplePref(default, key, ::doublePreferencesKey)

fun <T, V> KotlinDataStoreModelWithScope<V>.saveAsDoublePref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, Double>
) = dataStorePref(default, key, prefDataConverter, ::doublePreferencesKey)


fun <V> KotlinDataStoreModelWithScope<V>.stringPref(
    default: String = "",
    key: String? = null
) = simplePref(default, key, ::stringPreferencesKey)

fun <T, V> KotlinDataStoreModelWithScope<V>.saveAsStringPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, String>
) = dataStorePref(default, key, prefDataConverter, ::stringPreferencesKey)


fun <V> KotlinDataStoreModelWithScope<V>.booleanPref(
    default: Boolean = false,
    key: String? = null
) = simplePref(default, key, ::booleanPreferencesKey)

fun <T, V> KotlinDataStoreModelWithScope<V>.saveAsBooleanPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, Boolean>
) = dataStorePref(default, key, prefDataConverter, ::booleanPreferencesKey)


fun <V> KotlinDataStoreModelWithScope<V>.floatPref(
    default: Float = 0f,
    key: String? = null
) = simplePref(default, key, ::floatPreferencesKey)

fun <T, V> KotlinDataStoreModelWithScope<V>.saveAsFloatPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, Float>
) = dataStorePref(default, key, prefDataConverter, ::floatPreferencesKey)


fun <V> KotlinDataStoreModelWithScope<V>.longPref(
    default: Long = 0,
    key: String? = null
) = simplePref(default, key, ::longPreferencesKey)

fun <T, V> KotlinDataStoreModelWithScope<V>.saveAsLongPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, Long>
) = dataStorePref(default, key, prefDataConverter, ::longPreferencesKey)


fun <V> KotlinDataStoreModelWithScope<V>.stringSetPref(
    default: Set<String> = emptySet(),
    key: String? = null
) = simplePref(default, key, ::stringSetPreferencesKey)

fun <T, V> KotlinDataStoreModelWithScope<V>.saveAsStringSetPref(
    default: T,
    key: String? = null,
    prefDataConverter: DataStorePrefDataConverter<T, Set<String>>
) = dataStorePref(default, key, prefDataConverter, ::stringSetPreferencesKey)


internal fun <T, V> KotlinDataStoreModelWithScope<V>.simplePref(
    default: T,
    key: String? = null,
    initializer: (preferenceKey: String) -> Preferences.Key<T>
): SimpleDataStorePref<T, V> = dataStorePref(
    default, key,
    SimpleDataStorePrefDataConverter(),
    initializer
)

// ======================================================

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
