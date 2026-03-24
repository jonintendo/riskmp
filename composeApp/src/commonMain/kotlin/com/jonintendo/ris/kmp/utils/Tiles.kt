package com.jonintendo.ris.kmp.utils

import systems.untangle.karta.network.TileServer


data class TileServerOption(
    val name: String,
    val server: TileServer
)

val humanitaire = TileServerOption(
    "Humanitaire",
    TileServer("https://a.tile.openstreetmap.fr/hot/{zoom}/{x}/{y}.png")
)
val cyclo = TileServerOption(
    "CyclOSM",
    TileServer("https://a.tile-cyclosm.openstreetmap.fr/cyclosm/{zoom}/{x}/{y}.png")
)