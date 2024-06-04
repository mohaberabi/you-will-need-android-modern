package com.example.coilguide

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil.annotation.ExperimentalCoilApi
import coil.imageLoader
import com.example.coilguide.ui.theme.CachedImage
import com.example.coilguide.ui.theme.CoilGuideTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoilGuideTheme {

                CachedImage(

                    model = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTKvPvFeSc7TgFR8VGuPgJHa2HNvbQEsv0vuHHmrJEpAA&s"
                )
            }
        }
    }


    @OptIn(ExperimentalCoilApi::class)
    private fun clearCoilCache() {
        imageLoader.diskCache?.clear()
        imageLoader.memoryCache?.clear()
    }
}



