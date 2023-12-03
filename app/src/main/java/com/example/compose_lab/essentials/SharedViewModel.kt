package com.example.compose_lab.essentials

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_lab.components.Container
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SharedViewModel : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter:StateFlow<Int> = _counter

    fun incrementCounter() {
        _counter.value += 1
    }

    fun decrementCounter() {
        _counter.value -= 1
    }
}
@Composable
fun SharedViewModelComponent(){
    val viewModel: SharedViewModel = viewModel()
    Container(
        content = {
            FirstComposable(viewModel = viewModel)
            SecondComposable(viewModel = viewModel)
        }
    )
}

@Composable
fun FirstComposable(viewModel: SharedViewModel){
    val counterState by viewModel.counter.collectAsState()
    Text("First Composable")
    Text("Counter: $counterState")
    Button(onClick = { viewModel.incrementCounter() }){
        Text(text ="Increment" )
    }
}

@Composable
fun SecondComposable(viewModel: SharedViewModel){
    val counterState by viewModel.counter.collectAsState()
    Text("Second Composable")
    Text("Counter: $counterState")
    Button(onClick = { viewModel.decrementCounter() }){
        Text(text ="Decrement" )
    }
}