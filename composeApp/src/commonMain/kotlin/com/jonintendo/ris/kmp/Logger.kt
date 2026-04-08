package com.jonintendo.ris.kmp

interface Logger {
    fun e(tag:String, message: String)
    fun i(tag:String, message: String)
    fun d(tag:String, message: String)
}

expect fun getLogger(): Logger
