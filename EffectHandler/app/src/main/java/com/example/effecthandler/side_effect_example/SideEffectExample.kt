package com.example.effecthandler.side_effect_example

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect


@Composable


// the side effect is effect hadneler that
// is called whenver the recomposition is done okay each time [recomposition]
fun SideEffectScreenExample(
    nonComposeCounter: Int
) {


    SideEffect {
        println("Called after each okay recomposition")
    }
}