package com.example.photopicker13.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage


@OptIn(ExperimentalFoundationApi::class)
@Composable

fun SwiperScreen() {
    var selectedImages by remember {

        mutableStateOf<List<Uri>>(emptyList())

    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(),
        onResult = { uris ->

            if (uris.isNotEmpty()) {
                val list = mutableListOf<Uri>()
                list.addAll(selectedImages)
                list.addAll(uris)
                selectedImages = list
            }

        }
    )


    val state = rememberPagerState(pageCount = { selectedImages.size }, initialPage = 0)


    Box(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(state = state, key = { selectedImages[it] }
        ) {

                index ->
            val image = selectedImages[index]

            AsyncImage(
                model = image,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

        }
    }
}