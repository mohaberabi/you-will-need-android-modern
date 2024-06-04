package com.example.shimmereffect.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ItemScreen(
    isLoading :Boolean,
    modifier: Modifier=Modifier,

){

if (isLoading){

    LazyColumn{

        items(count =20){
            ShimmerItem()
        }

    }
}
}

@Composable
fun ShimmerItem(){
    Row(modifier = Modifier.padding(16.dp)) {
        Box(modifier = Modifier
            .size(100.dp)
            .shimmerEffect())
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier =Modifier.weight(1f)) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .shimmerEffect())
            Spacer(modifier = Modifier.height(12.dp))
            Box(modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(16.dp)
                .shimmerEffect())
        }
    }
}