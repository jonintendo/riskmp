package com.jonintendo.ris.kmp.utils

import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource
import systems.untangle.karta.data.Coordinates
import com.jonintendo.ris.kmp.Res
import com.jonintendo.ris.kmp.bluePin
import com.jonintendo.ris.kmp.greenPin
import com.jonintendo.ris.kmp.redPin
import systems.untangle.karta.selection.ItemSelectionState


val redPin = Res.drawable.redPin
val bluePin = Res.drawable.bluePin
val greenPin = Res.drawable.greenPin

fun choosePinResource(itemState: ItemSelectionState): DrawableResource {
    return if (itemState.selected) greenPin
    else if (itemState.hovered) bluePin
    else redPin
}

val darkBlue = Color(0xFF00008B)
val lightBlue = Color(0xFFA0A0FF)
val lightGray = Color(0xFFA0A0A0)



data class PointOfInterest(
    val name: String,
    var coordinates: Coordinates
)
