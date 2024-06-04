package com.example.retrofitguide.presentation.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.retrofitguide.data.model.ProductX


@Composable

fun ProductCard(product: ProductX) {

    Card(modifier = Modifier.padding(16.dp)) {

        Column(modifier = Modifier.padding(16.dp)) {

            AsyncImage(
                model = rememberAsyncImagePainter(model = product.thumbnail),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )

            Text(text = product.title)
        }
    }
}