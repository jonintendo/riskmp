package com.jonintendo.ris.kmp

import androidx.compose.runtime.Composable
import com.jonintendo.ris.kmp.buttons.MapButton

@Composable
fun AvisosView(mainViewModel: MainViewModel){
    MapButton({ mainViewModel.showAviso() }, "VOLTAR")

}