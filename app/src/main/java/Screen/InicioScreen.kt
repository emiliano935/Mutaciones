package Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mutaciones.ui.theme.MutacionesTheme

@Composable
fun InicioScreen(modifier: Modifier){
    var sup by rememberSaveable{ mutableStateOf("") }
    Column(modifier =modifier.fillMaxSize()){
        TextField(
            value = sup ,
            onValueChange = {dato -> sup=dato},
            modifier = Modifier.fillMaxWidth(),
            placeholder ={Text("0")})
        Spacer(modifier= Modifier.padding(10.dp))
        Row(modifier = Modifier) {
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
            , onClick = {}) { Text("7")}
            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                , onClick = {}) { Text("8")}
            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                , onClick = {}) { Text("9")}
            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                , colors = ButtonColors(containerColor = Color.Green,
                    contentColor =Color.Black,
                    disabledContainerColor = Color.Cyan,
                    disabledContentColor = Color.Cyan)
                , onClick = {}) { Text("*")}
        }
        Spacer(modifier= Modifier.padding(4.dp))
        Row(modifier = Modifier) {
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                , onClick = {}) { Text("4")}
            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                , onClick = {}) { Text("5")}
            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                , onClick = {}) { Text("6")}
            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                ,colors = ButtonColors(disabledContentColor =Color.Green ,
                    contentColor = Color.Black,
                    containerColor = Color.Green,
                    disabledContainerColor = Color.Green)
                , onClick = {}) { Text("/")}
        }
        Spacer(modifier= Modifier.padding(4.dp))
        Row(modifier = Modifier) {
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                , onClick = {}) { Text("1")}
            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                , onClick = {}) { Text("2")}
            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                , onClick = {}) { Text("4")}
            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                ,colors = ButtonColors(disabledContentColor =Color.Green ,
                    contentColor = Color.Black,
                    containerColor = Color.Green,
                    disabledContainerColor = Color.Green)
                , onClick = {}) { Text("-")}
        }
        Spacer(modifier= Modifier.padding(4.dp))
        Row(modifier = Modifier) {
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                , onClick = {}) { Text("0")}
            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                ,colors = ButtonColors(disabledContentColor =Color.Green ,
                    contentColor = Color.Black,
                    containerColor = Color.Green,
                    disabledContainerColor = Color.Green)
                , onClick = {}) { Text("CE")}
            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                ,colors = ButtonColors(disabledContentColor =Color.Green ,
                    contentColor = Color.Black,
                    containerColor = Color.Green,
                    disabledContainerColor = Color.Green)
                , onClick = {}) { Text("=")}
            Spacer(modifier = Modifier.padding(3.dp))
            Button(modifier = Modifier
                .width(90.dp)
                .height(50.dp)
                ,colors = ButtonColors(disabledContentColor =Color.Green,
                    contentColor = Color.Black,
                    containerColor = Color.Green,
                    disabledContainerColor = Color.Green)
                , onClick = {}) { Text("+")}
        }
    }
}


@Preview(showBackground = true)
@Composable
fun InicioScreenPreview(){
    MutacionesTheme{
        InicioScreen(modifier=Modifier.fillMaxSize())
    }
}