package com.example.compose_lab.essentials

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

import com.example.compose_lab.components.Container


class ForSavedStateLessonViewModel(
    private val savedStateHandle: SavedStateHandle
):ViewModel(){
    var counter by mutableStateOf<Int>(savedStateHandle.get<Int>("counter")?:0)
        private  set


    fun increase(){
        counter++
        savedStateHandle["counter"] = counter
        val last=savedStateHandle.get<Int>("counter")
        println("saved $last")

    }
}

@Composable
fun SavedStateLesson(
){
   val vm:ForSavedStateLessonViewModel= viewModel()
    Container(content = {
        Button(onClick ={vm.increase()} ) {
           Text(text = "Increase ${vm.counter}")
        }
    })
}