package Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mutaciones.ui.theme.MutacionesTheme

@Composable
fun InicioScreen(modifier: Modifier) {
    Column(modifier = Modifier) {
        var x by rememberSaveable {mutableStateOf("0") }
        var v by rememberSaveable {mutableStateOf("0") }
        var c by rememberSaveable {mutableStateOf("0") }

        TextField(value = x, onValueChange = {textonuevo -> x=textonuevo})
        Spacer(modifier = Modifier.padding(10.dp))
        TextField(value = v, onValueChange = {textonuevo -> v=textonuevo})
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick ={
            c=(x.toDouble()+v.toDouble()).toString()
        } ){
            Text("sumar")
        }
        Text(c, fontSize = 50.sp)
    }
}

@Preview
@Composable
fun inicioscreenpreview(){
    MutacionesTheme{
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            InicioScreen(modifier= Modifier.padding(innerPadding))
        }
    }
}