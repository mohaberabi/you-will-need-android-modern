package com.example.paging3

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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.paging3.presentation.MovieViewModel
import com.example.paging3.presentation.screen.MoviesScreen
import com.example.paging3.ui.theme.Paging3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Paging3Theme {

                val viewModel :MovieViewModel = hiltViewModel()
                val movies = viewModel.movies.collectAsLazyPagingItems()
                MoviesScreen(movies =movies )
            }
        }
    }
}

