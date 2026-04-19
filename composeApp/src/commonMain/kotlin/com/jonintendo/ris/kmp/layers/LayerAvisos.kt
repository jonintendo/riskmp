package com.jonintendo.ris.kmp.layers

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.jonintendo.ris.kmp.MainViewModel
import com.jonintendo.ris.kmp.Res
import com.jonintendo.ris.kmp.utils.bluePin
import com.jonintendo.ris.kmp.utils.rota
import com.jonintendo.ris.kmp.warning
import systems.untangle.karta.composables.Marker
import systems.untangle.karta.composables.Polyline
import systems.untangle.karta.composables.Sprite
import systems.untangle.karta.data.DoubleOffset
import systems.untangle.karta.data.PxSize
import systems.untangle.karta.data.px

@Composable
fun LayerAvisos(viewModel: MainViewModel) {
    val avisosMarkers = viewModel.avisos.avMarkerPoints()
    val avisosPolygons = viewModel.avisos.avPolygonsPoints()

    avisosMarkers.forEach {
        Marker(
            coords = it,
            //coordsSetter = { coords -> homeCoords = coords},
            anchoring = DoubleOffset(0.5, 1.0)
        ) {
            Sprite(
                resource = Res.drawable.warning,
                dimensions = PxSize(30.px, 48.px)
            )
        }
    }


    avisosPolygons.forEach { polygons ->
        Polyline(
            coordsList = polygons,
            strokeColor = Color.Black,
            fillColor = Color.Red,
            fillAlpha = 0.6f,
        )
    }

}