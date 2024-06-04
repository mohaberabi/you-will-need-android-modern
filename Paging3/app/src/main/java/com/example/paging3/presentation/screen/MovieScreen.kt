package com.example.paging3.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.example.paging3.domain.model.Movie


@Composable

fun MoviesScreen(movies:LazyPagingItems<Movie>){




    val context = LocalContext.current



    LaunchedEffect(movies.loadState){
        if (movies.loadState.refresh is LoadState.Error){
            Toast.makeText(context,(movies.loadState.refresh as LoadState.Error).error.message,Toast.LENGTH_SHORT).show()
        }


    }

    if (movies.loadState.refresh is LoadState.Loading){
        CircularProgressIndicator()
    }
    else {
        LazyColumn{
       items(movies.itemSnapshotList.items){

           Text(text = it.title)
       }
        }
    }




}