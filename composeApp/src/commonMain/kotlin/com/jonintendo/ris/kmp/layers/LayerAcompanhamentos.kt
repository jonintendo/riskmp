package com.jonintendo.ris.kmp.layers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.jonintendo.ris.kmp.MainViewModel
import com.jonintendo.ris.kmp.utils.bluePin
import systems.untangle.karta.composables.Marker
import systems.untangle.karta.composables.Sprite
import systems.untangle.karta.data.DoubleOffset
import systems.untangle.karta.data.PxSize
import systems.untangle.karta.data.px

@Suppress("SuspiciousIndentation")
@Composable
fun LayerAcompanhamentos(viewModel: MainViewModel) {
    // val ff by viewModel.simulatedCoord.collectAsState()
    viewModel.acompanhamentos_.forEach {
        Marker(
            coords = it,
            //coordsSetter = { coords -> homeCoords = coords},
            anchoring = DoubleOffset(0.5, 1.0)
        ) {
            Sprite(
                resource = bluePin,
                dimensions = PxSize(30.px, 48.px)
            )
        }
    }
}