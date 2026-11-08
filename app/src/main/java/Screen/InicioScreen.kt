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
import com.example.mutaciones.ui.theme.MutacionesTheme

@Composable
fun InscripcionScreen(modifier: Modifier) {
    var inscripciones by rememberSaveable { mutableStateOf(10) }
    var nombre by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var edad by rememberSaveable { mutableStateOf("") }
    var taller by rememberSaveable { mutableStateOf("") }
    var intentoEnviar by remember { mutableStateOf(false) }
    var inscrito by remember { mutableStateOf(false) }
    var aceptaAviso by remember { mutableStateOf(false) }

    if (inscrito) ConfirmacionScreen(nombre, correo, taller, onNueva = { /* limpiar */ })
   // else InscripcionForm(/* valores + callbacks */)
}


@Composable
fun ConfirmacionScreen(
    nombre: String,
    correo: String,
    taller: String,
    onNueva: () -> Unit
) {


}

@Composable
fun InscripcionForm(
    nombre: String, onNombreChange: (String) -> Unit, errorNombre: String?,
    correo: String, onCorreoChange: (String) -> Unit, errorCorreo: String?,
    edad: String, onEdadChange: (String) -> Unit, errorEdad: String?,
    taller: String, onTallerChange: (String) -> Unit,
    aceptaAviso: Boolean, onAceptaAvisoChange: (Boolean) -> Unit, errorAviso: String?,
    onSubmit: () -> Unit
) {
    var expansion by rememberSaveable { mutableStateOf(false) }
    val talleresdisponibles = listOf("Pintura", "Fotografia", "Guitarra")
    Column(modifier = Modifier.fillMaxSize()) {
        Text("")
    }
}


@Preview(showBackground = true)
@Composable
fun InicioScreenPreview() {
    MutacionesTheme {
        InscripcionScreen(modifier = Modifier.fillMaxSize())
    }
}