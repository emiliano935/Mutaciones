package Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

enum class Interlineado(val factor: Float) {
    COMPACTO(1.0f),
    NORMAL(1.3f),
    AMPLIO(1.6f)
}

data class AjustesLectura(
    val tamano: Float = 22f,
    val interlineado: Interlineado = Interlineado.NORMAL,
    val modoOscuro: Boolean = false,
    val usaSerif: Boolean = true
)

@Composable
fun LecturaScreen() {
    var ajustes by remember { mutableStateOf(AjustesLectura()) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = if (ajustes.modoOscuro) Color.Black else Color.White
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            ControlesLectura(
                ajustes = ajustes,
                onAjustesChange = { ajustes = it },
                onRestablecer = { ajustes = AjustesLectura() }
            )

            VistaPrevia(ajustes = ajustes)
        }
    }
}

@Composable
fun ControlesLectura(
    ajustes: AjustesLectura,
    onAjustesChange: (AjustesLectura) -> Unit,
    onRestablecer: () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text("Ajustes de lectura", fontSize = 22.sp)
        Spacer(modifier = Modifier.height(10.dp))

        // Tamaño de letra
        Text("Tamaño de letra: ${ajustes.tamano.toInt()} sp")
        Slider(
            value = ajustes.tamano,
            onValueChange = { onAjustesChange(ajustes.copy(tamano = it)) },
            valueRange = 14f..32f
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Interlineado
        Text("Interlineado")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Interlineado.values().forEach { tipo ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = ajustes.interlineado == tipo,
                        onClick = { onAjustesChange(ajustes.copy(interlineado = tipo)) }
                    )
                    Text(tipo.name.lowercase())
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Modo oscuro
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Modo oscuro")
            Switch(
                checked = ajustes.modoOscuro,
                onCheckedChange = { onAjustesChange(ajustes.copy(modoOscuro = it)) }
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Fuente
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Fuente")
            FilterChip(
                selected = ajustes.usaSerif,
                onClick = { onAjustesChange(ajustes.copy(usaSerif = true)) },
                label = { Text("Serif") }
            )
            FilterChip(
                selected = !ajustes.usaSerif,
                onClick = { onAjustesChange(ajustes.copy(usaSerif = false)) },
                label = { Text("Sans") }
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = onRestablecer,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Restablecer")
        }
    }
}

@Composable
fun VistaPrevia(ajustes: AjustesLectura) {
    val tipoFuente = if (ajustes.usaSerif) FontFamily.Serif else FontFamily.SansSerif
    val fondoCaja = if (ajustes.modoOscuro) Color.DarkGray else Color(0xFFEEEEEE)
    val colorLetra = if (ajustes.modoOscuro) Color.White else Color.Black

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(fondoCaja)
            .padding(16.dp)
    ) {
        Column {
            Text("VISTA PREVIA", fontSize = 11.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "Era una noche tranquila en el pueblo cuando las campanas comenzaron a sonar, y nadie supo por qué.",
                fontSize = ajustes.tamano.sp,
                fontFamily = tipoFuente,
                lineHeight = (ajustes.tamano * ajustes.interlineado.factor).sp,
                color = colorLetra
            )
        }
    }
}

// Preview para ver la pantalla directo en Android Studio
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LecturaScreenPreview() {
    LecturaScreen()
}