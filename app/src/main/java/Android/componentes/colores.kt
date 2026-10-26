package Android.componentes

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Colors (modifier: Modifier, nombre:String) {

    Column(modifier = Modifier) {
        Row(modifier= Modifier) {

        }
        Row(modifier = Modifier.fillMaxSize().height(70.dp)
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(12.dp)
            )
        ){
            when(nombre.lowercase()){


            }

        }

        Text(nombre)
    }
    Spacer(modifier = Modifier.padding(20.dp))
}