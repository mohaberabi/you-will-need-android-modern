package com.example.effecthandler.launched_effect_example

import android.os.CountDownTimer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay
import java.util.Timer


@Composable
fun LaunchedEffectScreen() {


    var pressedManyTimes by remember {

        mutableIntStateOf(0)
    }
    var text by remember {
        mutableStateOf(
            "Iam The reason of side effect if i was changed"
        )
    }

    var timerText by remember {
        mutableStateOf(
            "-"
        )
    }

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(text = text, style = MaterialTheme.typography.bodyMedium.copy(color = Color.Red))

        // a composable function
        // the key parameter is given such that if it was changed anytime
        // the current coroutine scope will be canceled then relaunched again to be executed

        // that takes varargs when any of them changes the scope is relaucnehd again
        LaunchedEffect(key1 = text) {

            val timer = object : CountDownTimer(10000L, 1000L) {
                override fun onTick(milli: Long) {

                    timerText = milli.toString()
                }

                override fun onFinish() {
                    cancel()
                }

            }
            timer.start()

        }
        Text(text = "Below is a timer that will be reset each time the launched effect state changed by changing the text variable")
        Text(text = timerText, style = MaterialTheme.typography.headlineMedium)

        Button(
            onClick = {
                pressedManyTimes++
                text = "the loser pressed the button ${pressedManyTimes} times "
            }
        ) {

            Text(text = "Press me to change the text  ")
        }

    }


}



