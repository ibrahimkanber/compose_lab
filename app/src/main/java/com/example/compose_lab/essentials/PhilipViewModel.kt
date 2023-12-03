package com.example.compose_lab.essentials

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_lab.components.Container
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PhilipViewModelCompose(private val initialColor: Color) : ViewModel() {
    var backgroundColor by mutableStateOf(initialColor)

    fun changeBackgroundColor() {
        Log.d("change", "bg")
        backgroundColor = if (backgroundColor == initialColor) Color.Green else initialColor

    }
}


class PhilipViewModelFactory(private val initialBackgroundColor: Color) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhilipViewModelCompose::class.java)) {
            return PhilipViewModelCompose(initialBackgroundColor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

@Composable
fun PhilipHomeFeed() {
    val viewModal: PhilipViewModelCompose = viewModel(factory = PhilipViewModelFactory(Color.Red))
    Container(
        content = {
            Button(onClick = {
                viewModal.changeBackgroundColor()

            }) {
                Text(text = "Click me")
            }

        },
        color=viewModal.backgroundColor

    )
}

class PhilipViewModelFlow(
    private val initialColor: Color

) : ViewModel() {
    private val _color = MutableStateFlow(initialColor)
    val color = _color.asStateFlow()
    fun changeBackgroundColor() {
        Log.d("change", "bg")
        _color.value = if (_color.value ==initialColor) Color.Green else initialColor

    }
}



class PhilipViewModelFactory2(private val initialBackgroundColor: Color) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhilipViewModelFlow::class.java)) {
            return PhilipViewModelFlow(initialBackgroundColor) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}






@Composable
fun PhilipHomeFeed2() {

    val viewModal: PhilipViewModelFlow = viewModel(factory = PhilipViewModelFactory2(Color.Magenta))

    val color by viewModal.color.collectAsState()
    Container(
        content = {
            Button(onClick = {
                viewModal.changeBackgroundColor()
            }) {
                Text(text = "Click me")
            }

        },
        color=color

    )
}


