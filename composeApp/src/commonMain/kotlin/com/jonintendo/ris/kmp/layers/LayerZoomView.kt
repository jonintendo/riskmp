package com.jonintendo.ris.kmp.layers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.layout
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.dp
import com.jonintendo.ris.kmp.utils.darkBlue
import com.jonintendo.ris.kmp.utils.lightBlue
import systems.untangle.karta.data.ZoomLevel

@Composable
fun LayerZoomView(zoom: ZoomLevel){

    Box(
        modifier = Modifier.fillMaxSize().padding(5.dp),
        contentAlignment = Alignment.BottomStart
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
                .shadow(elevation = 30.dp, shape = RoundedCornerShape(8.dp))
                .background(Color.LightGray)
                .padding(5.dp)
        ) {
            Button(
                onClick = { zoom.increment() },
                modifier = Modifier.size(25.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = darkBlue,
                    contentColor = Color.White
                )
            ) {
                Text("+")
            }

            // how to properly rotate a container
            // https://stackoverflow.com/a/71129399

            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .graphicsLayer {
                        rotationZ = 270f
                        transformOrigin = TransformOrigin(0f, 0f)
                    }
                    .layout { measurable, constraints ->
                        val placeable = measurable.measure(
                            Constraints(
                                minWidth = constraints.minHeight,
                                maxWidth = constraints.maxHeight,
                                minHeight = constraints.minWidth,
                                maxHeight = constraints.maxHeight,
                            )
                        )
                        layout(placeable.height, placeable.width) {
                            placeable.place(-placeable.width, 0)
                        }
                    }
                    .width(150.dp)
                    .height(25.dp)
            ) {
                Slider(
                    value = zoom.level.toFloat(),
                    onValueChange = { },
                    valueRange = 2.0f..19.0f,
                    steps = 18,
                    colors = SliderDefaults.colors(
                        activeTickColor = darkBlue,
                        activeTrackColor = darkBlue,
                        inactiveTickColor = lightBlue,
                        inactiveTrackColor = lightBlue,
                        thumbColor = darkBlue
                    )
                )
            }

            Button(
                onClick = { zoom.decrement() },
                modifier = Modifier.size(25.dp),
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = darkBlue,
                    contentColor = Color.White
                )
            ) {
                Text("-")
            }
        }
    }

}