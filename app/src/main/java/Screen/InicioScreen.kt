package Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun  InicioScreen(modifier: Modifier) {
    Column(modifier = Modifier) {
        var x by rememberSaveable { mutableStateOf("añadir color") }
        val listaElementos = rememberSaveable { mutableStateListOf<String>() }

        Column(
            modifier = Modifier.fillMaxWidth()
                .height(200.dp)
        ) {

            TextField(
                value = x,
                onValueChange = { escrito -> x = escrito },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Button(onClick = {
                if (x.isNotEmpty()) {
                    listaElementos.add(x)
                    x = ""
                }
            }) { Text("Agregar") }

        }
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            items(listaElementos) { colors ->
                colors(modifier = modifier, colors)

            }
        }

    }
}