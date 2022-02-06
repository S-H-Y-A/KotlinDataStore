package com.s_h_y_a.kotlindatastoresample

import androidx.lifecycle.LifecycleCoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

inline fun <T> Flow<T>.collectWhenCreated(
    lifecycleScope: LifecycleCoroutineScope,
    crossinline onChanged: suspend (value: T) -> Unit
): Job =
    lifecycleScope.launchWhenCreated {
        this@collectWhenCreated.collect(onChanged)
    }

inline fun <T> Flow<T>.collectWhenStarted(
    lifecycleScope: LifecycleCoroutineScope,
    crossinline onChanged: suspend (value: T) -> Unit
): Job =
    lifecycleScope.launchWhenStarted {
        this@collectWhenStarted.collect(onChanged)
    }

inline fun <T> Flow<T>.collectWhenResumed(
    lifecycleScope: LifecycleCoroutineScope,
    crossinline onChanged: suspend (value: T) -> Unit
): Job =
    lifecycleScope.launchWhenResumed {
        this@collectWhenResumed.collect(onChanged)
    }
