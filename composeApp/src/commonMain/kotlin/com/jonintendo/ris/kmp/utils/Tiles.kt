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

val noland= TileServerOption(
    "No_Land",
    TileServer("https://acquavia.acquaway.com/mapas/noland/{zoom}/{y}/{x}.png")
)


val encbase= TileServerOption(
    "Enc_Base",
TileServer("https://acquavia.acquaway.com/mapas/{zoom}/{y}/{x}.png?layers=enc_base"))

val encstandard= TileServerOption(
    "Enc_Standard",
TileServer("https://acquavia.acquaway.com/mapas/{zoom}/{y}/{x}.png?layers=enc_standard,shape"))

val encall= TileServerOption(
    "Enc_All",
TileServer("https://acquavia.acquaway.com/mapas/{zoom}/{y}/{x}.png?layers=enc_all,shape"))