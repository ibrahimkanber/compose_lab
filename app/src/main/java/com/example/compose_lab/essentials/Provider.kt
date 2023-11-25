package com.example.compose_lab.essentials

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

private val LocalCounter = staticCompositionLocalOf<Int> { error("No Counter provided") }





@Composable
fun Provider() {
    var counter by remember { mutableStateOf(0) }
    CompositionLocalProvider(LocalCounter provides  counter) {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = Color(0xFFF5CCA0)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Count: ${LocalCounter.current}")
            Button(onClick = { counter++ }) {
                Text("Increment Count")
            }
            ChildConsumer()
        }
    }

}



@Composable
fun ChildConsumer() {
val count= LocalCounter.current
    Text(text ="current val from ChildConsumer  $count")
}
