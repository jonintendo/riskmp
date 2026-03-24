package com.jonintendo.ris.kmp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jonintendo.ris.kmp.layers.LayerButtonsView
import com.jonintendo.ris.kmp.layers.LayerMissionView
import com.jonintendo.ris.kmp.layers.LayerRotasView
import com.jonintendo.ris.kmp.utils.PointOfInterest
import com.jonintendo.ris.kmp.utils.home
import com.jonintendo.ris.kmp.utils.humanitaire
import systems.untangle.karta.Karta
import systems.untangle.karta.base.LocalConverter
import systems.untangle.karta.base.LocalCursor
import systems.untangle.karta.base.LocalViewingBoundingBox
import systems.untangle.karta.base.LocalZoom
import systems.untangle.karta.conversion.latitudeDMS
import systems.untangle.karta.conversion.longitudeDMS
import systems.untangle.karta.input.PointerPosition
import systems.untangle.karta.popup.Popup
import systems.untangle.karta.popup.PopupItem
import systems.untangle.karta.popup.rememberPopupContext
import systems.untangle.karta.selection.rememberSelectionContext

@Composable
fun KartaView2(viewModel: MainViewModel) {

    val tileServer = remember { mutableStateOf(humanitaire) }
    val createdPins = remember { mutableStateListOf<PointOfInterest>() }
    val movableCreatedPins = remember { mutableStateListOf<PointOfInterest>() }
    val createdCircles = remember { mutableStateListOf<PointOfInterest>() }


    val selectionContext = rememberSelectionContext()
    val popupContext = rememberPopupContext()

    val interactive = remember { mutableStateOf(true) }


    val options = remember(createdPins) {
        listOf(
            PopupItem("Create new Pin") { coords ->
                val pinName = "PIN${createdPins.size + movableCreatedPins.size}"
                val poi = PointOfInterest(pinName, coords)
                createdPins.add(poi)
            },

            PopupItem("Create new Circle") { coords ->
                val pinName = "CIRCLE${createdCircles.size}"
                val poi = PointOfInterest(pinName, coords)
                createdCircles.add(poi)
            },

            PopupItem("Start new Polyline") { println("LILI") }
        )
    }

    suspend fun openPopup(pointerPosition: PointerPosition) {
        val coordinates = pointerPosition.coordinates
        popupContext.show(
            coordinates,
            options
        )
    }

    val onLongPress = remember {
        mutableStateOf<suspend (PointerPosition) -> Unit>(
            { pointerPosition ->
                val coordinates = pointerPosition.coordinates
                println(coordinates)
                popupContext.show(
                    coordinates,
                    options
                )
            }
        )
    }

    Karta(
        tileServer = tileServer.value.server,
        initialCoords = home,
        initialZoom = 14,
        interactive = interactive.value,
        onMapDragged = {
            popupContext.hide()
        },

        onPress = {
            selectionContext.clearSelection()
            popupContext.hide()
        },

        onLongPress = onLongPress.value
    ) {
        val cursor = LocalCursor.current
        val viewingRegion = LocalViewingBoundingBox.current
        val converter = LocalConverter.current
        val zoom = LocalZoom.current

        if (popupContext.hasContents) {
            Popup(popupContext)
        }

        if (null != cursor) {
            Box(
                modifier = Modifier.fillMaxSize().padding(5.dp),
                contentAlignment = Alignment.TopEnd
            ) {
                Row(
                    modifier = Modifier
                        .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                        .shadow(elevation = 30.dp, shape = RoundedCornerShape(8.dp))
                        .background(Color.LightGray)
                        .padding(8.dp),

                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(latitudeDMS(cursor.latitude))
                    Text(longitudeDMS(cursor.longitude))
                }
            }
        }


        LayerMissionView(viewModel)
        // LayerCreatedView(selectionContext,popupContext,createdPins,movableCreatedPins,createdCircles)
        //LayerZoomView(zoom)
        LayerButtonsView(viewModel, tileServer, interactive, onLongPress)
       // LayerExampleView(selectionContext)
        LayerRotasView(viewModel)
    }
}