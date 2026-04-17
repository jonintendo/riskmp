package com.jonintendo.ris.kmp

import net.sf.marineapi.nmea.parser.SentenceFactory
import net.sf.marineapi.nmea.sentence.GGASentence
import net.sf.marineapi.nmea.sentence.GSASentence
import net.sf.marineapi.nmea.sentence.RMCSentence
import systems.untangle.karta.data.Coordinates

actual fun nmeaParser(nmea: String):Coordinates? {
    val sf = SentenceFactory.getInstance()
    val sentence = sf.createParser(nmea)
    if (sentence is RMCSentence) {
        println("Latitude: ${sentence.position.latitude}")
        println("Longitude: ${sentence.position.longitude}")
        return Coordinates(sentence.position.latitude,sentence.position.longitude)
    }else{
        return null
    }
}