package com.jonintendo.ris.kmp

import androidx.lifecycle.ViewModel
import com.jonintendo.ris.kmp.mission.Mission
import com.jonintendo.ris.kmp.rotas.Rota
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    val rota = Rota()
    val mission = Mission()

    private val frame_ = MutableStateFlow("sdf")
    val frame: StateFlow<String> = frame_.asStateFlow()
    fun changeFrame() {
        frame_.value = "nossaaaa!!!!"
    }
}