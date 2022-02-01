package com.s_h_y_a.kotlindatastore.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.s_h_y_a.kotlindatastore.DataStorePrefDataConverter
import com.s_h_y_a.kotlindatastore.DataStorePrefDataConverterWrapper
import com.s_h_y_a.kotlindatastore.KotlinDataStoreModel
import kotlinx.coroutines.flow.Flow
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

typealias SimpleDataStoreFlowPref<T, V> = DataStoreFlowPref<T, T, V>
/**
 * T:変換する型
 * R:SharedPreferencesとして保存する型
 * Provide DataStorePref as Flow
 */
class DataStoreFlowPref<T, R, V> internal constructor(
    dataStore: DataStore<Preferences>,
    key: String? = null,
    defaultValue: T,
    converter: DataStorePrefDataConverter<T, R>,
    preferenceKeyFactory: (preferenceKey: String) -> Preferences.Key<R>
) : AbstractDataStorePref<T, R, V>(
    dataStore,
    key,
    defaultValue,
    converter,
    preferenceKeyFactory
), ReadOnlyProperty<KotlinDataStoreModel<V>, Flow<T>> {

    override val converterWrapper = object : DataStorePrefDataConverterWrapper<T, R>(converter, defaultValue) {
        override suspend fun save(value: T) {
            dataStore.edit { settings ->
                settings[id] = this.encode(value)
            }
        }
    }

    override fun getValue(thisRef: KotlinDataStoreModel<V>, property: KProperty<*>) = dataStoreFlow

    override operator fun provideDelegate(
        dataStore: KotlinDataStoreModel<V>,
        property: KProperty<*>
    ): DataStoreFlowPref<T, R, V> {
        super.provideDelegate(dataStore, property)
        return this
    }
}