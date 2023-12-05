package com.example.compose_lab.essentials

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_lab.components.Container
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

data class Lesson(
    var name:String,
    var id:Int
)
class SelfStudyViewModel:ViewModel(){
   private  val _count= MutableStateFlow(0)
    private  val _lesson= MutableStateFlow<Lesson>(Lesson("Math",1))
    private  val _lessons= MutableStateFlow(listOf<Lesson>())
    val count=_count.asStateFlow()
    val lesson =_lesson.asStateFlow()
    val lessonList =_lessons.asStateFlow()
    fun increase(){
        _count.value++
    }
    fun change(newName:String){
        _lesson.value=_lesson.value.copy(name=newName)
    }

    fun createNewLesson(lessonName:String){
        val newId=_lessons.value.count()+1
        val lessonCandidate=Lesson(lessonName,newId)
        val currentList = _lessons.value.toMutableList()
        currentList.add(lessonCandidate)
        _lessons.value = currentList
    }
    fun removeLesson(lesson:Lesson){

        val currentList = _lessons.value.toMutableList()
        currentList.remove(lesson)
        _lessons.value = currentList
    }
}
@Composable
fun SelfStudy1(){
    val vm:SelfStudyViewModel= viewModel()
    val count by vm.count.collectAsState()
    val lesson by vm.lesson.collectAsState()
    val lessonList by vm.lessonList.collectAsState()
    Container(content = {
        Button(onClick = {vm.increase()},content={ Text(text = "hi $count")})
        Text(text ="Lesson ${lesson.name}" )
        Button(onClick = {vm.change("English")},content={ Text(text = "change")})
        Button(onClick = {vm.createNewLesson("English")},content={ Text(text = "+")})
        LazyColumn{
            items(lessonList) { lesson ->
                key(lesson.id) {
                    Row {
                        Button(onClick = {vm.removeLesson(lesson)},content={ Text(text = "-")})
                        Text(text = "Lesson ID: ${lesson.id}, Name: ${lesson.name}")
                    }

                }
            }
        }
    })
}


