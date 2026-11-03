package Screen

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
import com.example.mutaciones.ui.theme.MutacionesTheme

@Composable
fun AforoScreen(modifier: Modifier){
    var actuales by rememberSaveable{ mutableStateOf(0)}
    var visitasdia by rememberSaveable {mutableStateOf(0)}
    val capacidadmax=50
    val porcentaje = actuales /capacidadmax.toFloat()
    val porciento=(porcentaje*100).toInt()

    val tarjeta = when {
        porciento >= 100 -> Color(0xFFFFEBEE)
        porciento >= 70  -> Color(0xFFFFFD_E7)
        else -> Color(0xFFE8F5E9)
    }

    val texto = when {
        porciento >= 100 -> Color(0xFFC62828)
        porciento >= 70  -> Color(0xFFF57F17)
        else                 -> Color(0xFF1B5E20)
    }


    Column(modifier = Modifier) {
        Spacer(modifier = Modifier.padding(3.dp))
        Text("Bibloteca Central",
            fontSize = 22.sp)
        Spacer(modifier=modifier.padding(5.dp))
        Text("Sala de estudio - capacidad 50")
        Spacer(modifier = Modifier.padding(2.dp))
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(color = tarjeta)
            .padding(40.dp)
            , contentAlignment = Alignment.Center
            ,
        ){
            Column(modifier = Modifier
                , horizontalAlignment = Alignment.CenterHorizontally) {
                Text("$actuales/$capacidadmax",
                    fontSize = 80.sp,
                    color = texto)
                Text("personas en la sala")
                Spacer(Modifier.padding(7.dp))
                LinearProgressIndicator(modifier = Modifier
                    .height(10.dp)
                    ,progress = {actuales*0.02f}
                    , color = texto
                )
                Spacer(Modifier.padding(8.dp))
                Text("$porciento % de ocupacion",
                    fontSize = 23.sp
                , color = texto)
                Spacer(Modifier.padding(3.dp))
                Text("Aforo disponible"
                    ,fontSize = 12.sp)
            }
        }
        Spacer(Modifier.padding(8.dp))
        Row(modifier = Modifier) {
            Spacer(Modifier.padding(15.dp))
            Button(onClick ={if(actuales!=0){
                actuales--
            } }
                ,Modifier.height(60.dp)
                    .width(150.dp))
            {Text("- sale", fontSize = 25.sp)
            }
            Spacer(Modifier.padding(15.dp))
            Button(onClick = {if(actuales<50){
                actuales++
                visitasdia++
            } }
                ,Modifier.height(60.dp)
                    .width(150.dp)) { Text("+ entra",
                fontSize = 25.sp)}
        }
        Spacer(Modifier.padding(4.dp))
        Row(Modifier.fillMaxWidth()
            .padding(8.dp)
            .background(Color.LightGray)){
            Column{
                Spacer(Modifier.padding(4.dp))
                Text("visitas del dia")
                Spacer(Modifier.padding(2.dp))
                Text("solo suma  las entradas")
                Spacer(Modifier.padding(4.dp))
            }
            Box(modifier = Modifier.fillMaxWidth()
            ){
                Column(modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("$visitasdia", fontSize = 70.sp)
                }
            }
        }
        Spacer(Modifier.padding(10.dp))
        Row(modifier = Modifier) {
            Row {
                Spacer(Modifier.padding(horizontal =10.dp))
                Box(modifier = Modifier
                    .size(15.dp)
                    .background(Color.Green, shape = CircleShape))
                Spacer(Modifier.padding(horizontal =2.dp))
                Text("verde < 70 %", fontSize = 15.sp)
            }
            Spacer(Modifier.padding(horizontal = 5.dp))
            Row {
                Box(modifier = Modifier
                    .size(15.dp)
                    .background(Color.Yellow, shape = CircleShape))
                Spacer(Modifier.padding(horizontal =2.dp))
                Text("amarillo < 70-99 %", fontSize = 15.sp)
            }
            Spacer(Modifier.padding(horizontal = 5.dp))
            Row {
                Box(modifier = Modifier
                    .size(15.dp)
                    .background(Color.Red, shape = CircleShape))
                Spacer(Modifier.padding(horizontal =2.dp))
                Text("rojo 100 %", fontSize = 15.sp)
            }

        }
        Spacer(Modifier.padding(20.dp))
        Button(onClick ={
            actuales=0
            visitasdia=0
        }
            , modifier = Modifier.fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 10.dp)
            , border = BorderStroke(2.dp,Color.Red)
            , colors = ButtonColors(
                disabledContainerColor = Color.Red,
                disabledContentColor = Color.White
                , contentColor = Color.Red
                , containerColor = Color.White)) {
            Text("cerrar dia")
        }
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