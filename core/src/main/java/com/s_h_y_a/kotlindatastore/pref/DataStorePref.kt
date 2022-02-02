package com.s_h_y_a.kotlindatastore.pref

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.s_h_y_a.kotlindatastore.DataStorePrefDataConverter
import com.s_h_y_a.kotlindatastore.DataStorePrefDataConverterWrapper
import com.s_h_y_a.kotlindatastore.KotlinDataStoreModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

typealias SimpleDataStorePref<T, V> = DataStorePref<T, T, V>

/**
 * T 変換する型
 * R SharedPreferencesとして保存する型
 */
class DataStorePref<T, R, V> internal constructor(
    private val scope: CoroutineScope,
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
), ReadWriteProperty<KotlinDataStoreModel<V>, T> {

    override val converterWrapper = object : DataStorePrefDataConverterWrapper<T, R>(converter, defaultValue) {
        override suspend fun save(value: T) {
            dataStore.edit { settings ->
                settings[id] = this.encode(value)
            }
        }
/* TODO: ObservedSet
        override fun convertDefaultValue(value: T): T {
            return if (value is MutableSet<*>) {
                val set = ObservedSet(value)
                set.notice = {
                    scope.launch {
                        save(it as T)
                    }
                }
                set as T
            } else {
                value
            }
        }

        override fun beforeDecode(value: R?): R? {
            return if (value is Set<*>) {
                val set = ObservedSet(LinkedHashSet(value))
                set as R
            } else {
                value
            }
        }

        override fun afterDecode(value: T): T {
            return if (value is Set<*>) {
                val set = ObservedSet(LinkedHashSet(value))
                set.notice = {
                    scope.launch {
                        save(it as T)
                    }
                }
                set as T
            } else {
                value
            }
        }*/
    }

    override fun getValue(thisRef: KotlinDataStoreModel<V>, property: KProperty<*>): T = runBlocking {
        try {
            dataStoreFlow.first()
        } catch (e: NoSuchElementException) {
            Log.e("KotlinDataStore", "Because of NoSuchElementException, returned default value.")
            defaultValue
        }
    }

    override fun setValue(
        thisRef: KotlinDataStoreModel<V>,
        property: KProperty<*>,
        value: T
    ) {
        dataStoreFlow.emitIn(scope, value)
    }

    override operator fun provideDelegate(
        dataStore: KotlinDataStoreModel<V>,
        property: KProperty<*>
    ): DataStorePref<T, R, V> {
        super.provideDelegate(dataStore, property)
        //prefetch
        scope.launch {
            dataStoreFlow.first()
        }
        return this
    }
}