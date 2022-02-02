package com.s_h_y_a.kotlindatastore.enumdatastore

import com.s_h_y_a.kotlindatastore.DataStorePrefDataConverter
import com.s_h_y_a.kotlindatastore.KotlinDataStoreModel
import com.s_h_y_a.kotlindatastore.pref.saveAsIntFlowPref
import com.s_h_y_a.kotlindatastore.pref.saveAsStringFlowPref

inline fun <reified E : Enum<E>, V> KotlinDataStoreModel<V>.enumNameFlowPref(
    default: E,
    key: String? = null
) = saveAsStringFlowPref(
    default,
    key,
    object : DataStorePrefDataConverter<E, String> {
        override suspend fun encode(value: E?) = (value?: default).name

        override suspend fun decode(savedValue: String?, defaultValue: E): E {
            return savedValue?.let { enumValueOf<E>(it) } ?: defaultValue
        }

    }
)

inline fun <reified E : Enum<E>, V> KotlinDataStoreModel<V>.enumOrdinalFlowPref(
    default: Enum<E>,
    key: String? = null
) = saveAsIntFlowPref(
    default,
    key,
    object : DataStorePrefDataConverter<Enum<E>, Int> {
        override suspend fun encode(value: Enum<E>?) = (value?: default).ordinal

        override suspend fun decode(savedValue: Int?, defaultValue: Enum<E>): Enum<E> {
            return enumValues<E>().firstOrNull { it.ordinal == savedValue } ?: defaultValue
        }

    }
)