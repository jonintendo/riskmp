package com.jonintendo.ris.kmp.buttons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jonintendo.ris.kmp.utils.lightGray

@Composable
fun MapButton(onclick: () -> Unit, text: String,color: Color = lightGray) {
    Button(
        modifier = Modifier
            .width(120.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(8.dp))
            .height(35.dp),

        onClick = {
            onclick()
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = color,
            contentColor = Color.White
        )
    ) {
        Text(text)
    }
}