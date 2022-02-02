package com.s_h_y_a.kotlindatastore.moshi

import com.s_h_y_a.kotlindatastore.KotlinDataStoreModel
import com.s_h_y_a.kotlindatastore.pref.saveAsStringFlowPref
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

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