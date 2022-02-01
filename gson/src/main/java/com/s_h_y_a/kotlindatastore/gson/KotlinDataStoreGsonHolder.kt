package com.s_h_y_a.kotlindatastore.gson

import android.annotation.SuppressLint
import com.google.gson.Gson

@SuppressLint("StaticFieldLeak")
object KotlinDataStoreGsonHolder {
    var gson: Gson = Gson()
}
