package com.s_h_y_a.kotlindatastore

// T 変換前
// R 保存用
interface DataStorePrefDataConverter<T, R> {
    suspend fun encode(value: T?): R
    suspend fun decode(savedValue: R?, defaultValue: T): T
}

class SimpleDataStorePrefDataConverter<T> internal constructor(): DataStorePrefDataConverter<T, T>{
    override suspend fun encode(value: T?): T = value!!
    override suspend fun decode(savedValue: T?, defaultValue: T): T = savedValue ?: defaultValue
}
