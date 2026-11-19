package Screen

import ViewModel.AforoViewModel
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun AforoScreen(
    modifier: Modifier = Modifier,
    viewModel: AforoViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val tarjeta = when {
        state.porciento >= 100 -> Color(0xFFFFEBEE)
        state.porciento >= 70  -> Color(0xFFFFFDE7)
        else                   -> Color(0xFFE8F5E9)
    }
    val texto = when {
        state.porciento >= 100 -> Color(0xFFC62828)
        state.porciento >= 70  -> Color(0xFFF57F17)
        else                   -> Color(0xFF1B5E20)
    }

    Column(modifier = modifier.padding(15.dp)) {
        Spacer(Modifier.padding(3.dp))
        Text("Biblioteca Central", fontSize = 22.sp)
        Spacer(Modifier.padding(5.dp))
        Text("Sala de estudio - capacidad ${state.capacidadMax}")
        Spacer(Modifier.padding(2.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = tarjeta)
                .padding(40.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("${state.actuales}/${state.capacidadMax}", fontSize = 80.sp, color = texto)
                Text("personas en la sala")
                Spacer(Modifier.padding(7.dp))
                LinearProgressIndicator(
                    modifier = Modifier.height(10.dp),
                    progress = { state.porcentaje },
                    color = texto
                )
                Spacer(Modifier.padding(8.dp))
                Text("${state.porciento} % de ocupacion", fontSize = 23.sp, color = texto)
                Spacer(Modifier.padding(3.dp))
                Text("Aforo disponible", fontSize = 12.sp)
            }
        }

        Spacer(Modifier.padding(8.dp))
        Row {
            Spacer(Modifier.padding(15.dp))
            Button(
                onClick = viewModel::sale,
                modifier = Modifier.height(60.dp).width(150.dp)
            ) { Text("- sale", fontSize = 25.sp) }
            Spacer(Modifier.padding(15.dp))
            Button(
                onClick = viewModel::entra,
                modifier = Modifier.height(60.dp).width(150.dp)
            ) { Text("+ entra", fontSize = 25.sp) }
        }

        Spacer(Modifier.padding(4.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(Color.LightGray)
        ) {
            Column {
                Spacer(Modifier.padding(4.dp))
                Text("visitas del dia")
                Spacer(Modifier.padding(2.dp))
                Text("solo suma  las entradas")
                Spacer(Modifier.padding(4.dp))
            }
            Box(Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("${state.visitasDia}", fontSize = 70.sp)
                }
            }
        }

        Spacer(Modifier.padding(10.dp))
        Row {
            Row {
                Spacer(Modifier.padding(horizontal = 10.dp))
                Box(Modifier.size(15.dp).background(Color.Green, CircleShape))
                Spacer(Modifier.padding(horizontal = 2.dp))
                Text("verde < 70 %", fontSize = 15.sp)
            }
            Spacer(Modifier.padding(horizontal = 5.dp))
            Row {
                Box(Modifier.size(15.dp).background(Color.Yellow, CircleShape))
                Spacer(Modifier.padding(horizontal = 2.dp))
                Text("amarillo 70-99 %", fontSize = 15.sp)
            }
            Spacer(Modifier.padding(horizontal = 5.dp))
            Row {
                Box(Modifier.size(15.dp).background(Color.Red, CircleShape))
                Spacer(Modifier.padding(horizontal = 2.dp))
                Text("rojo 100 %", fontSize = 15.sp)
            }
        }

        Spacer(Modifier.padding(20.dp))
        Button(
            onClick = viewModel::cerrarDia,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 10.dp),
            border = BorderStroke(2.dp, Color.Red),
            colors = ButtonColors(
                containerColor = Color.White,
                contentColor = Color.Red,
                disabledContainerColor = Color.Red,
                disabledContentColor = Color.White
            )
        ) { Text("cerrar dia") }
    }
}

@Preview
@Composable
fun AforoScreenPreview() {
    MutacionesTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            AforoScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}