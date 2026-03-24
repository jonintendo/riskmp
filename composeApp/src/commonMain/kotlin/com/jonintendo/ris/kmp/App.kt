package com.jonintendo.ris.kmp

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun App() {
    val viewModel: MainViewModel = viewModel()
    KartaView2(viewModel)

}
