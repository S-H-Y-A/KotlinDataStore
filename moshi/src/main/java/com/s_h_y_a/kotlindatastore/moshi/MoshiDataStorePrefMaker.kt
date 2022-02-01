package com.s_h_y_a.kotlindatastore.moshi

import com.s_h_y_a.kotlindatastore.KotlinDataStoreModel
import com.s_h_y_a.kotlindatastore.KotlinDataStoreModelWithScope
import com.s_h_y_a.kotlindatastore.pref.saveAsStringFlowPref
import com.s_h_y_a.kotlindatastore.pref.saveAsStringPref
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

inline fun <reified T, V> KotlinDataStoreModelWithScope<V>.moshiPref(
    default: T,
    key: String? = null,
    moshi: Moshi = KotlinDataStoreMoshiHolder.moshi
) = saveAsStringPref(
    default,
    key,
    moshiDataStorePrefDataConverter(moshi.adapter(T::class.java))
)

fun <T, V> KotlinDataStoreModelWithScope<V>.moshiTypePref(
    type: Type,
    default: T,
    key: String? = null,
    moshi: Moshi = KotlinDataStoreMoshiHolder.moshi
) = saveAsStringPref(
    default,
    key,
    moshiDataStorePrefDataConverter(moshi.adapter(type))
)

// ======================================================

inline fun <reified T, V> KotlinDataStoreModel<V>.moshiFlowPref(
    default: T,
    key: String? = null,
    moshi: Moshi = KotlinDataStoreMoshiHolder.moshi
) = saveAsStringFlowPref(
    default,
    key,
    moshiDataStorePrefDataConverter(moshi.adapter(T::class.java))
)

fun <T, V> KotlinDataStoreModel<V>.moshiTypeFlowPref(
    type: Type,
    default: T,
    key: String? = null,
    moshi: Moshi = KotlinDataStoreMoshiHolder.moshi
) = saveAsStringFlowPref(
    default,
    key,
    moshiDataStorePrefDataConverter(moshi.adapter(type))
)