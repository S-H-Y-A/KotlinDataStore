package com.s_h_y_a.kotlindatastore

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.s_h_y_a.kotlindatastore.pref.DataStoreFlowPref
import kotlinx.coroutines.runBlocking

abstract class KotlinDataStoreModel<V>(
    name: String = "",
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>? = null,
    produceMigrations: (Context) -> List<DataMigration<Preferences>> = { listOf() }
) {
    val name: String = name.ifBlank { javaClass.simpleName }

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
        this.name,
        corruptionHandler,
        produceMigrations
    )
    internal val datastore by lazy { KotlinDataStore.staticContext.dataStore }

    suspend fun emitAll(block: suspend (queue: PreferenceQueue<V>) -> Unit) {
        datastore.edit {
            block(PreferenceQueue(it))
        }
    }

    inner class PreferenceQueue<V> internal constructor(private val settings: MutablePreferences) {
         operator fun<T, R> set(flow: DataStoreFlow<T, R, V>, value: T) {
            settings[flow.id] = runBlocking { flow.encode(value) }
        }
    }

// ======================================================

    internal fun <T, R> dataStoreFlowPref(
        default: T,
        key: String? = null,
        prefDataConverter: DataStorePrefDataConverter<T, R>,
        preferenceKeyFactory: (key: String) -> Preferences.Key<R>
    ) = DataStoreFlowPref<T, R, V>(
        datastore,
        key,
        default,
        prefDataConverter,
        preferenceKeyFactory
    )
}

