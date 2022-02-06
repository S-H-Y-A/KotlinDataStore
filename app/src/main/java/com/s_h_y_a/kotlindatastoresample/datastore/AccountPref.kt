package com.s_h_y_a.kotlindatastoresample.datastore

import com.s_h_y_a.kotlindatastore.KotlinDataStoreModel
import com.s_h_y_a.kotlindatastore.enumdatastore.enumNameFlowPref
import com.s_h_y_a.kotlindatastore.pref.intFlowPref
import com.s_h_y_a.kotlindatastore.pref.stringFlowPref
import com.s_h_y_a.kotlindatastore.pref.stringSetFlowPref
import com.s_h_y_a.kotlindatastoresample.model.Sex

object AccountPref :
    KotlinDataStoreModel<AccountPref>() {
    val sex by enumNameFlowPref(Sex.NotSelected)
    val age by intFlowPref()
    val name by stringFlowPref()
    val friends by stringSetFlowPref()
}