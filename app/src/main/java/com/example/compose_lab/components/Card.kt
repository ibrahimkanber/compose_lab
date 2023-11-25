package com.example.compose_lab.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CardComponent(){
    Card(
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )

    ) {
        Text(text = "Hello1, world!")
    }
}