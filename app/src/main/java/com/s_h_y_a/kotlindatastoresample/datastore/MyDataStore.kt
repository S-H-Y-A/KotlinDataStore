package com.s_h_y_a.kotlindatastoresample.datastore

import com.s_h_y_a.kotlindatastore.KotlinDataStoreModel
import com.s_h_y_a.kotlindatastore.enumdatastore.enumNameFlowPref
import com.s_h_y_a.kotlindatastore.pref.intFlowPref
import com.s_h_y_a.kotlindatastoresample.model.Sex

object MyDataStore : KotlinDataStoreModel<MyDataStore>() {
    val sex by enumNameFlowPref(Sex.NotSelected)
    val old by intFlowPref()

    suspend fun saveAll(sex: Sex) {

        emitAll {
            it[this.sex] = sex
        }
    }
}