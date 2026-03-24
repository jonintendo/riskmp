package com.jonintendo.ris.kmp.layers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jonintendo.ris.kmp.MainViewModel
import com.jonintendo.ris.kmp.buttons.MapButton
import com.jonintendo.ris.kmp.buttons.MenuButtonOptions
import com.jonintendo.ris.kmp.utils.TileServerOption
import com.jonintendo.ris.kmp.utils.cyclo
import com.jonintendo.ris.kmp.utils.humanitaire
import org.jetbrains.compose.resources.stringResource
import systems.untangle.karta.input.PointerPosition
import com.jonintendo.ris.kmp.Res
import com.jonintendo.ris.kmp.rotas

@Composable
fun LayerButtonsView(
    viewModel: MainViewModel,
    tileServer: MutableState<TileServerOption>,
    interactive: MutableState<Boolean>,
    onLongPress: MutableState<suspend (PointerPosition) -> Unit>,
) {

    val frame by viewModel.frame.collectAsState()
    val rotas = stringResource(Res.string.rotas)


    var menuButtonOptions by remember { mutableStateOf(MenuButtonOptions.INICIO) }

    Box(
        modifier = Modifier.fillMaxSize().padding(5.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            when (menuButtonOptions) {
                MenuButtonOptions.INICIO -> {
                    MenuButtonOptions.entries.map { buttonOption ->
                        if (buttonOption != MenuButtonOptions.INICIO) {
                            MapButton({ menuButtonOptions = buttonOption }, buttonOption.name)
                        }
                    }
                }

                MenuButtonOptions.MAPAS -> {
                    MapButton({ tileServer.value = humanitaire }, "Humanitaire")
                    MapButton({ tileServer.value = cyclo }, "Cyclo")
                }

                MenuButtonOptions.MISSAO -> {
                    MapButton({ viewModel.mission.clearWaypoints() }, "Clear")
                    MapButton({
                        onLongPress.value = { position ->
                            viewModel.mission.addWaypoint(position.coordinates)
                        }
                        println("${onLongPress.value}sssssssssssssssssssssssssssssssssssssssssssssssss")
                    }, "Set")

                    MapButton({ viewModel.mission.moveWayPoint(1) }, "Move")
                }

                MenuButtonOptions.ROTAS -> {
                    MapButton({ viewModel.rota.clearWayPoints() }, "CLEAR")
                    MapButton({ viewModel.rota.detectRotasWayPoints(rotas) }, "GET")
                }

                MenuButtonOptions.TESTES -> {
                    MapButton({ viewModel.changeFrame() }, "${frame}")
                    MapButton({ interactive.value = !interactive.value }, "Interactive")
                }
            }

            if (menuButtonOptions != MenuButtonOptions.INICIO)
                MapButton(
                    { menuButtonOptions = MenuButtonOptions.INICIO }, MenuButtonOptions.INICIO.name
                )
        }
    }
}

