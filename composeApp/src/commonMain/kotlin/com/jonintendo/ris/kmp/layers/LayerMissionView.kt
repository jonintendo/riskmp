package com.jonintendo.ris.kmp.layers

import androidx.compose.runtime.Composable
import com.jonintendo.ris.kmp.MainViewModel
import systems.untangle.karta.composables.Marker
import systems.untangle.karta.composables.Sprite
import systems.untangle.karta.data.DoubleOffset
import systems.untangle.karta.data.PxSize
import systems.untangle.karta.data.px
import com.jonintendo.ris.kmp.utils.bluePin

@Composable
fun LayerMissionView( viewModel: MainViewModel){


    viewModel.mission.waypoints().forEach {
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