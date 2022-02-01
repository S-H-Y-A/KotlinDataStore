package com.s_h_y_a.kotlindatastore

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.s_h_y_a.kotlindatastore.pref.DataStorePref
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

abstract class KotlinDataStoreModelWithScope<V>(
    private val scope: CoroutineScope,
    name: String = "",
    corruptionHandler: ReplaceFileCorruptionHandler<Preferences>? = null,
    produceMigrations: (Context) -> List<DataMigration<Preferences>> = { listOf() }
): KotlinDataStoreModel<V>(name,corruptionHandler, produceMigrations) {

    fun saveAll(block: suspend (queue: PreferenceQueue<V>) -> Unit) {
        scope.launch {
            datastore.edit {
                block(PreferenceQueue(it))
            }
        }
    }



    inner class PreferenceQueue<V> internal constructor(private val settings: MutablePreferences) {
         operator fun<T, R> set(flow: DataStoreFlow<T, R, V>, value: T) {
            settings[flow.id] = runBlocking { flow.encode(value) }
        }
    }

// ======================================================


    //TODO: Check
    fun <T, R, V: KotlinDataStoreModelWithScope<V>> DataStoreFlow<T, R, V>.emitInParentScope(value: T) {
        this.emitIn(scope, value)
    }

    internal fun <T, R> dataStorePref(
        default: T,
        key: String? = null,
        prefDataConverter: DataStorePrefDataConverter<T, R>,
        preferenceKeyFactory: (key: String) -> Preferences.Key<R>
    ) = DataStorePref<T, R, V>(
        scope,
        datastore,
        key,
        default,
        prefDataConverter,
        preferenceKeyFactory
    )
}

