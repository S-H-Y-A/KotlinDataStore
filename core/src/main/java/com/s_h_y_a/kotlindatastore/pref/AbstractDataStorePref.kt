package com.s_h_y_a.kotlindatastore.pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.s_h_y_a.kotlindatastore.*
import kotlinx.coroutines.flow.map
import kotlin.reflect.KProperty

abstract class AbstractDataStorePref<T, R, V> internal constructor(
    private val dataStore: DataStore<Preferences>,
    private val key: String? = null,
    protected val defaultValue: T,
    private val converter: DataStorePrefDataConverter<T, R>,
    preferenceKeyFactory: (preferenceKey: String) -> Preferences.Key<R>
)  {
    private var propertyName: String? = null
    private val preferenceKey: String
        get() = key ?: propertyName
        ?: throw IllegalStateException("Use delegated properties or set preference key.")

    protected val id: Preferences.Key<R> by lazy { preferenceKeyFactory(preferenceKey) }

    internal abstract val converterWrapper : DataStorePrefDataConverterWrapper<T, R>

    protected val dataStoreFlow by lazy {
        DataStoreFlow<T, R, V>(
            dataStore.data.map { converterWrapper.decode(it[id]) },
            converterWrapper,
            id
        )
    }

    open operator fun provideDelegate(
        dataStore: KotlinDataStoreModel<V>,
        property: KProperty<*>
    ): AbstractDataStorePref<T, R, V> {
        this.propertyName = property.name
        return this
    }
}