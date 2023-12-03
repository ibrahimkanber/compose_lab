package com.example.compose_lab.essentials

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_lab.components.Container
import com.google.android.engage.common.datamodel.Image
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class IbrahimViewModel : ViewModel() {
    private val _stateFlowValue = MutableStateFlow<String>("Hello")
    val stateFlowValue = _stateFlowValue.asStateFlow()

    private val _sharedFlowValue = MutableSharedFlow<String>()
    val sharedFlowValue = _sharedFlowValue.asSharedFlow()


    fun triggerStateFlow() {
        _stateFlowValue.value = "StateFlow"
    }

    fun triggerSharedFlow() {
        viewModelScope.launch {
            _sharedFlowValue.emit("Shared Flow")
        }
    }

    fun triggerFlow(): Flow<String> {
        return flow {
            repeat(5) {
                emit("Hello $it")
                delay(1000L)
            }
        }
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateLessonTwo() {
    val viewModel: IbrahimViewModel = viewModel()
    val stateDataValue by viewModel.stateFlowValue.collectAsState()
    val sharedDataValue by viewModel.sharedFlowValue.collectAsState("hi")

    val scope = rememberCoroutineScope()


    val snackbarHostState = remember { SnackbarHostState() }
    val launchEffectTrigger by remember { mutableStateOf(0) }


    LaunchedEffect(launchEffectTrigger) {

        viewModel.stateFlowValue.collectLatest {
            snackbarHostState.showSnackbar("Snackbar State")
        }
    }

    LaunchedEffect(launchEffectTrigger) {
        viewModel.sharedFlowValue.collectLatest {
            snackbarHostState.showSnackbar("Snackbar Shared")
        }
    }



    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show snackbar") },
                icon = { Icon(Icons.Filled.Info, contentDescription = "") },
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar("Snackbar")
                    }
                }
            )
        }
    ) {
        // Screen content
        Container(content = {
            Text(text = sharedDataValue)
            Text(text = stateDataValue)
            Button(onClick = {
                viewModel.triggerSharedFlow()
            }) {
                Text(text = "click me triggerSharedFlow")
            }
            Button(onClick = {
                viewModel.triggerStateFlow()
            }) {
                Text(text = "click me triggerStateFlow")
            }
        })
    }


}



