package com.jonintendo.ris.kmp.layers

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jonintendo.ris.kmp.MainViewModel
import systems.untangle.karta.composables.Polyline

@Composable
fun LayerRotasView(viewModel: MainViewModel){
  //  val rotasCoords by viewModel.rota.rotasCoords.collectAsState()
    val rotasCoords = viewModel.rota.waypoints()

    Polyline(
        coordsList = rotasCoords,
        strokeColor = Color.Red,
        strokeWidth = 5.0f
    )
}