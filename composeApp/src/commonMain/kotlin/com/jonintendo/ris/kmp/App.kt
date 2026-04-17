package com.jonintendo.ris.kmp

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun App() {
    val viewModel: MainViewModel = viewModel()
    val showAvisos by  viewModel.aviso.collectAsState()
    if (showAvisos)
        AvisosView(viewModel)
    else
        KartaView2(viewModel)

}
