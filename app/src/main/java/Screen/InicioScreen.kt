package Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable fun InscripcionScreen(modifier: Modifier) {
    var inscripciones by rememberSaveable{mutableStateOf(10) }
    var nombre by rememberSaveable{mutableStateOf("")}
    var correo by rememberSaveable{mutableStateOf("")}
    var edad  by rememberSaveable{mutableStateOf("")}
    var taller by rememberSaveable{mutableStateOf("")}
    if (inscrito) ConfirmacionScreen(nombre, correo, taller, onNueva = { /* limpiar */ })
    else InscripcionForm(/* valores + callbacks */)
}

@Composable
fun CampoTexto(label: String,
               value: String,
               onValueChange: (String) -> Unit,
               error: String?)
{

}

@Composable
fun ConfirmacionScreen(nombre: String,
                       correo: String,
                       taller: String,
                       onNueva: () -> Unit)
{

}


