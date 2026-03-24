package com.jonintendo.ris.kmp.layers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.graphics.Color
import com.jonintendo.ris.kmp.utils.PointOfInterest
import com.jonintendo.ris.kmp.utils.choosePinResource
import systems.untangle.karta.composables.Circle
import systems.untangle.karta.composables.Marker
import systems.untangle.karta.composables.MovableMarker
import systems.untangle.karta.composables.Sprite
import systems.untangle.karta.data.DoubleOffset
import systems.untangle.karta.data.PxSize
import systems.untangle.karta.data.px
import systems.untangle.karta.popup.PopupContext
import systems.untangle.karta.popup.PopupItem
import systems.untangle.karta.selection.SelectionFlowContext
import systems.untangle.karta.selection.SelectionItem

@Composable
fun LayerCreatedView(
    selectionContext: SelectionFlowContext,
    popupContext: PopupContext,
    createdPins: SnapshotStateList<PointOfInterest>,
    movableCreatedPins: SnapshotStateList<PointOfInterest>,
    createdCircles: SnapshotStateList<PointOfInterest>,
) {

    createdPins.forEach { poi ->
        SelectionItem(
            selectionContext = selectionContext,
            itemId = poi.name
        ) { itemState ->
            Marker(
                coords = poi.coordinates,
                itemSelectionState = itemState,
                anchoring = DoubleOffset(0.5, 1.0),
                onLongPress = {
                    popupContext.show(
                        poi.coordinates,
                        listOf(
                            PopupItem("Remove Pin") {
                                createdPins.remove(poi)
                            },

                            PopupItem("Unlock Pin") {
                                movableCreatedPins.add(poi)
                                createdPins.remove(poi)
                            }
                        ))
                }
            ) {
                Sprite(
                    resource = choosePinResource(itemState),
                    dimensions = PxSize(30.px, 48.px),
                )
            }
        }
    }

    movableCreatedPins.forEach { poi ->
        SelectionItem(
            selectionContext = selectionContext,
            itemId = poi.name
        ) { itemState ->
            MovableMarker(
                coords = poi.coordinates,
                coordsSetter = { coords -> poi.coordinates = coords },
                itemSelectionState = itemState,
                anchoring = DoubleOffset(0.5, 1.0),

                onLongPress = {
                    popupContext.show(
                        poi.coordinates,
                        listOf(
                            PopupItem("Remove Pin") {
                                movableCreatedPins.remove(poi)
                            },

                            PopupItem("Lock Pin") {
                                createdPins.add(poi)
                                movableCreatedPins.remove(poi)
                            }
                        ))
                }
            ) {
                Sprite(
                    resource = choosePinResource(itemState),
                    dimensions = PxSize(30.px, 48.px)
                )
            }
        }
    }

    createdCircles.forEach { poi ->
        SelectionItem(
            selectionContext = selectionContext,
            itemId = poi.name
        ) { itemState ->
            Circle(
                coords = poi.coordinates,
                radius = 10f,
                borderWidth = 1f,
                fillColor = if (itemState.selected) Color.Green else Color.Blue
            )
        }
    }
}