package Screen

import ViewModel.Interlineado
import ViewModel.LecturaViewModel
import android.R.attr.fontFamily
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mutaciones.ui.theme.MutacionesTheme



@Composable
fun LecturaScreen(
    modifier: Modifier = Modifier,
    viewModel: LecturaViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(vertical = 14.dp)
            .padding(horizontal = 12.dp)
    ) {
        Text("Ajustes de lectura", fontSize = 25.sp)
        Spacer(Modifier.padding(vertical = 13.dp, horizontal = 4.dp))
        Row {
            Text("tamano de letra", fontSize = 20.sp)
            Spacer(Modifier.padding(horizontal = 85.dp))
            Text("${state.tamano.toInt()} sp", fontSize = 17.sp, color = Color.Magenta)
        }
        ControlesLectura(
            tamano = state.tamano,
            onTamanoChange = viewModel::onTamanoChange,
            interlineado = state.interlineado,
            onInterlineadoChange = viewModel::onInterlineadoChange,
            modoOscuro = state.modoOscuro,
            onModoOscuroChange = viewModel::onModoOscuroChange,
            usaSerif = state.usaSerif,
            onUsaSerifChange = viewModel::onUsaSerifChange
        )
        Spacer(Modifier.padding(vertical = 50.dp))
        VistaPrevia(
            texto = "Era una noche tranquila en el pueblo cuando las campanas comenzaron a sonar, y nadie supo por qué.",
            tamano = state.tamano,
            interlineado = state.interlineado,
            modoOscuro = state.modoOscuro,
            usaSerif = state.usaSerif
        )
    }
}


@Composable
fun ControlesLectura(
    tamano: Float, onTamanoChange: (Float) -> Unit,
    interlineado: Interlineado, onInterlineadoChange: (Interlineado) -> Unit,
    modoOscuro: Boolean, onModoOscuroChange: (Boolean) -> Unit,
    usaSerif: Boolean, onUsaSerifChange: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Slider(
            value = tamano,
            onValueChange = onTamanoChange,
            valueRange = 14f..32f,
            colors = SliderDefaults.colors(
                thumbColor = Color.Magenta,
                activeTrackColor = Color.Magenta,
                inactiveTickColor = Color.LightGray
            )
        )
        Row {
            Text(text = "A", fontSize = 14.sp)
            Spacer(Modifier.padding(horizontal = 170.dp))
            Text(text = "A", fontSize = 22.sp)
        }
        Text(text = "Interlineado")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Interlineado.values().forEach { tipo ->
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = interlineado == tipo,
                        onClick = { onInterlineadoChange(tipo) },

                        )
                    Text(text = tipo.name)
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = "Modo oscuro")
            Spacer(Modifier.padding(horizontal = 110.dp))
            Switch(
                checked = modoOscuro,
                onCheckedChange = onModoOscuroChange,
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color.Magenta,
                    checkedBorderColor = Color.Magenta,
                )
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),

            ) {
            Text(text = "Fuente")

            Spacer(modifier = Modifier.width(160.dp))

            FilterChip(
                selected = usaSerif,
                onClick = { onUsaSerifChange(true) },
                label = { Text("Serif") }
            )
            FilterChip(
                selected = !usaSerif,
                onClick = { onUsaSerifChange(false) },
                label = { Text("Sans") }
            )
        }
    }
}


@Composable
fun VistaPrevia(
    texto: String,
    tamano: Float,
    interlineado: Interlineado,
    modoOscuro: Boolean,
    usaSerif: Boolean
) {
    Column(modifier = Modifier.fillMaxWidth())
    {
        Text(
            text = "VISTA PREVIA",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Modo oscuro
        val colorCard = if (modoOscuro) {
            CardDefaults.cardColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        } else {
            CardDefaults.cardColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            )
        }

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = colorCard
        ) {
            Box(modifier = Modifier.padding(20.dp))
            {
                Text(
                    text = texto,
                    fontSize = tamano.sp,
                    lineHeight = (tamano * interlineado.factor).sp,
                    fontFamily = if (usaSerif) FontFamily.Serif else FontFamily.SansSerif
                )
            }
        }
    }
}


@Preview
@Composable
fun InicioScreenPreview() {
    MutacionesTheme {
        LecturaScreen(modifier = Modifier.fillMaxSize())
    }
}
//