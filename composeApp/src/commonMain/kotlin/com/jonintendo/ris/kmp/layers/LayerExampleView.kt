package com.jonintendo.ris.kmp.layers

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.hideFromAccessibility
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jonintendo.ris.kmp.utils.aeroporto
import com.jonintendo.ris.kmp.utils.cefet
import com.jonintendo.ris.kmp.utils.choosePinResource
import com.jonintendo.ris.kmp.utils.home
import com.jonintendo.ris.kmp.utils.ilhaBoi
import com.jonintendo.ris.kmp.utils.rota
import systems.untangle.karta.composables.Circle
import systems.untangle.karta.composables.EditablePolyline
import systems.untangle.karta.composables.Marker
import systems.untangle.karta.composables.MovableMarker
import systems.untangle.karta.composables.Polyline
import systems.untangle.karta.composables.Sprite
import systems.untangle.karta.data.Coordinates
import systems.untangle.karta.data.DistanceUnit
import systems.untangle.karta.data.DoubleOffset
import systems.untangle.karta.data.PxSize
import systems.untangle.karta.data.px
import com.jonintendo.ris.kmp.utils.redPin
import systems.untangle.karta.selection.SelectionFlowContext
import systems.untangle.karta.selection.SelectionItem

@Composable
fun LayerExampleView(selectionContext: SelectionFlowContext) {

    var homeCoords by remember { mutableStateOf(home) }
    val cefetCoords by remember { mutableStateOf(cefet) }

    val aeroportCoords = remember {
        mutableStateListOf<Coordinates>().apply {
            addAll(aeroporto)
        }
    }

    SelectionItem(
        selectionContext = selectionContext,
        itemId = "home"
    ) { itemState ->
        for (k in 1..3) {
            Circle(
                coords = homeCoords,
                radius = k * 500f,
                radiusUnit = DistanceUnit.METERS,
                borderWidth = 2f,
                fillColor = null
            )
        }

        MovableMarker(
            coords = homeCoords,
            coordsSetter = { coords -> homeCoords = coords },
            itemSelectionState = itemState,
            anchoring = DoubleOffset(0.5, 1.0)
        ) {
            Sprite(
                resource = choosePinResource(itemState),
                //dimensions = PxSize(30.px, 48.px)
                dimensions = PxSize(40.px, 58.px)
            )
        }
    }

    SelectionItem(
        selectionContext = selectionContext,
        itemId = "cefet"
    ) { itemState ->
        Marker(
            coords = cefetCoords,
            itemSelectionState = itemState,
            anchoring = DoubleOffset(0.5, 1.0)
        ) {
            Sprite(
                resource = choosePinResource(itemState),
                dimensions = PxSize(30.px, 48.px)
            )
        }
    }


    Marker(
        coords = ilhaBoi
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Sprite(
                resource = redPin,
                dimensions = PxSize(30.px, 48.px)
            )

            Box {
                Text(
                    text = "Ilha do Boi",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.semantics { hideFromAccessibility() }
                        .shadow(elevation = 8.dp, shape = RectangleShape),
                    style = TextStyle(
                        shadow = null,
                        drawStyle = Stroke(1.5f)
                    )
                )

                Text(
                    text = "Ilha do Boi",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }

    Polyline(
        coordsList = rota,
        strokeColor = Color.Blue,
        strokeWidth = 5.0f,
    )



    EditablePolyline(
        coordsList = aeroportCoords.toList(),
        coordsSetter = { index, value -> aeroportCoords[index] = value },
        strokeColor = Color.Black,
        fillColor = Color.Green,
        fillAlpha = 0.6f,
        closed = true
    )

}