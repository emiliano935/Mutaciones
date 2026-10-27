package Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mutaciones.ui.theme.MutacionesTheme

@Composable
fun InicioScreen(modifier: Modifier){
    var sup by rememberSaveable{ mutableStateOf(0) }
    Column(modifier =modifier.fillMaxSize()){
       // TextField(value = sup , onValueChange = {cal -> sup=cal})
        Spacer(modifier= Modifier.padding(10.dp))
Row(modifier = Modifier) {
    Button()
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