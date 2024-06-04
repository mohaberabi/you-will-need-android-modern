package com.example.effecthandler.disposbale_effect_Example

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver


@Composable

fun DisposableEffectScreen() {
    val lifeCycleOwner = LocalLifecycleOwner.current


    // same as launched effect but this is disposbale when the compositiion is not needed or
    // the compoosbale function is outside the stack call frame
    //this is used when u need to call some functions or dow some word that
    // needs to be disposed whenever not needed again as avoid  of memeory leaks
    DisposableEffect(key1 = lifeCycleOwner) {

        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_PAUSE) {

                println(" ON PUASE")
            }
        }
        lifeCycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifeCycleOwner.lifecycle.removeObserver(observer)
        }
    }
}