package com.s_h_y_a.kotlindatastoresample.datastore

import com.s_h_y_a.kotlindatastore.KotlinDataStoreModelWithScope
import com.s_h_y_a.kotlindatastore.pref.stringPref
import kotlinx.coroutines.CoroutineScope

class MyDataStoreWithScope(coroutineScope: CoroutineScope) :
    KotlinDataStoreModelWithScope<MyDataStoreWithScope>(coroutineScope) {

    var x by stringPref()

    fun A(): Unit {
        x = ""
    }
}