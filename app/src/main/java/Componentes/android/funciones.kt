package Componentes.android

import android.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun Screeninicio(modifier: Modifier) {
   var sup by rememberSaveable{ mutableStateOf("") }
    var numero1 by rememberSaveable{mutableStateOf("") }
    operador by rememberSaveable{mutableStateOf("") }
    fun clicknumero(numero: String) {
         sup = sup + numero



    }

    fun operador (ope: R.string){
        if(sup!=""){
            numero1 = sup
            operador = ope
            sup=""
        }
    }
}