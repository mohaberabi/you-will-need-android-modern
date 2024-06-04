package com.example.effecthandler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.effecthandler.launched_effect_example.LaunchedEffectScreen
import com.example.effecthandler.launched_effect_example.launched_effect_with_viewmodel.LaunchedEffectViewModel
import com.example.effecthandler.launched_effect_example.launched_effect_with_viewmodel.LaunchedEffectViewModelScreen
import com.example.effecthandler.ui.theme.EffectHandlerTheme

class MainActivity : ComponentActivity() {
    val viewModel: LaunchedEffectViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EffectHandlerTheme {


//                LaunchedEffectScreen()

                LaunchedEffectViewModelScreen(viewModel = viewModel)
            }
        }
    }
}
