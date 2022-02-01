package com.s_h_y_a.kotlindatastore

internal abstract class DataStorePrefDataConverterWrapper<T, R>(
    private val converter: DataStorePrefDataConverter<T, R>,
    defaultValue: T
) {
    val defaultValue = convertDefaultValue(defaultValue)
    protected open fun convertDefaultValue(value: T) = value
    protected open fun beforeEncode(value: T): T = value
    protected open fun afterEncode(value: R): R = value
    protected open fun beforeDecode(value: R?) = value
    protected open fun afterDecode(value: T): T = value
    abstract suspend fun save(value: T)
    internal suspend fun encode(value: T) = afterEncode(converter.encode(beforeEncode(value)))
    internal suspend fun decode(value: R?) = afterDecode(converter.decode(beforeDecode(value), defaultValue))
}