package com.example.compose_lab.essentials

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.key
import com.example.compose_lab.components.Container
import kotlin.random.Random

data class UserState(
    var name: String,
    var age: Int
)


@Composable
fun StateLab() {
    Container {
        StateLabPrimitive()
        CustomDivider()
        StateLabObject()
        CustomDivider()
        StateLabList()
    }
}

@Composable
fun StateLabPrimitive() {
    var count by remember {
        mutableStateOf(0)
    }
    Title("-----UPDATE PRIMITIVE STATE-----")
    Button(onClick = { count++ }) {
        Text(text = "Click me  $count")
    }
}

@Composable
fun StateLabObject() {
    var user by remember { mutableStateOf(UserState("Tom", age = 0)) }
    Title("-----UPDATE OBJECT STATE-----")
    Text(text = "user age ${user.age}")
    Text(text = "user name ${user.name}")
    Button(onClick = {
        user = user.copy(age = user.age + 1)
    }) {
        Text(
            text = "Increase age  ${user.age}",
            color = androidx.compose.ui.graphics.Color.White
        )
    }
}


fun createRandomAge(minAge: Int, maxAge: Int): Int {
    require(minAge <= maxAge) { "minAge should be less than or equal to maxAge" }

    return Random.nextInt(minAge, maxAge + 1)
}

@Composable
fun StateLabList() {
    var userList by remember {
        mutableStateOf(
            listOf(
                UserState("Ibrahim", 30),
                UserState("John", 28)
            )
        )
    }
    Title("-----UPDATE LIST STATE-----")
    Button(onClick = {
        val newUser = UserState("New user", createRandomAge(30, 50))
        userList = userList + newUser
    }) {
        Text(
            text = "Add New user",
            color = androidx.compose.ui.graphics.Color.White
        )
    }

    LazyColumn {
        items(userList) { user ->
            key(user.age) {
                Text(text = "User ID: ${user.age}, Name: ${user.name}")
            }
        }
    }


}


@Composable
fun Title(title: String) {
    Text(
        text = "-----$title-----",
        modifier = Modifier.padding(horizontal = 0.dp, vertical = 20.dp)
    )
}

@Composable
fun CustomDivider() {
    Divider(
        thickness = 5.dp,
        color = androidx.compose.ui.graphics.Color.DarkGray,
        modifier = Modifier.padding(20.dp)
    )
}





