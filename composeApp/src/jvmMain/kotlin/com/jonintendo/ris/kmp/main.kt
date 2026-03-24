package com.jonintendo.ris.kmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "karta-sample",
    ) {
        App()
    }
}