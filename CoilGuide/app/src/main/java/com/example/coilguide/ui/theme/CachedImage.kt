package com.example.coilguide.ui.theme

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage


@Composable
fun CachedImage(model: Any? = null) {


    val context = LocalContext.current

    AsyncImage(

        model = model,
        contentDescription = "mohab the loser boy ",
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1280f / 847f)
    )
}