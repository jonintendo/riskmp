package com.jonintendo.ris.kmp

import systems.untangle.karta.data.Coordinates

expect fun nmeaParser(nmea:String): Coordinates?
