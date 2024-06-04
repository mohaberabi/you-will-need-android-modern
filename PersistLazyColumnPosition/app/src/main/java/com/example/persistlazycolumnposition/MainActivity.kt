package com.example.persistlazycolumnposition

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
import com.example.persistlazycolumnposition.ui.theme.PersistLazyColumnPositionTheme

class MainActivity : ComponentActivity() {
    private val prefs by lazy {

        applicationContext.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lastSavedPosition = prefs.getInt("lastPos", 0)
        setContent {
            PersistLazyColumnPositionTheme {

                HomeScreen(lastSavedPosition = lastSavedPosition) {
                    prefs.edit()
                        .putInt("lastPos", it)
                        .apply()
                }
            }
        }
    }
}

