package com.s_h_y_a.kotlindatastore.initializer

import android.content.Context
import androidx.startup.Initializer
import com.s_h_y_a.kotlindatastore.KotlinDataStore

class DataStoreInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        KotlinDataStore.init(context)
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}