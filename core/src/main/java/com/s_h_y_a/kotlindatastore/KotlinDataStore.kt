package com.s_h_y_a.kotlindatastore

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object KotlinDataStore {

    private var mStaticContext: Context? = null
    internal var staticContext: Context
        get() = mStaticContext
            ?: throw IllegalStateException("KotlinDataStore has not been initialized.")
        set(value) {
            mStaticContext = value
        }

    val isInitialized
        get() = mStaticContext != null

    fun init(context: Context) {
        staticContext = context
    }
}
