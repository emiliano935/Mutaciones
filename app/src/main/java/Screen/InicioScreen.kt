package Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mutaciones.ui.theme.MutacionesTheme

@Composable
fun InicioScreen(modifier: Modifier,numero: Int,miclick:()-> Unit){
    Column(modifier = Modifier) {
        Text(numero.toString(), fontSize = 50.sp)
        Button(onClick =miclick ){
            Text("sumar")
        }
    }
}


@Preview
@Composable
fun inicioscreenpreview(){
    MutacionesTheme {
        var x by rememberSaveable {mutableStateOf(0) }

        InicioScreen(modifier= Modifier.fillMaxSize().background(Color.White),x,{x=x+1} )
        }
    }
