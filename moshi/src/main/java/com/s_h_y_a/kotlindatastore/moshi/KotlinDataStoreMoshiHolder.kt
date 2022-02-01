package com.s_h_y_a.kotlindatastore.moshi

import android.annotation.SuppressLint
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

@SuppressLint("StaticFieldLeak")
object KotlinDataStoreMoshiHolder {
    var moshi: Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()!!
}
