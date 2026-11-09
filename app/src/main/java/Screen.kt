import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mutaciones.ui.theme.MutacionesTheme
import kotlin.collections.listOf


data class Producto(val nombre: String, val precio: Double)

@Composable
fun CarritoScreen(modifier: Modifier) {
    val catalogo = remember {
        listOf(
            Producto("tortillas 1 kg", 24.00),
            Producto("Leche 1 L", 27.50),
            Producto("Huevo 12 pz", 52.00),
            Producto("tortillas 1 kg", 38.00)
        )
    }
    val cantidades = remember { mutableStateListOf(0, 0, 0, 0) }
    // derivados:
    val totalarticulos = cantidades.sum()
    val subtotal = catalogo.indices.sumOf { i -> catalogo[i].precio * cantidades[i] }
    val descuento = if (subtotal >= 200.0) {
        subtotal * 0.10
    } else 0.0
    val subtotalConDescuento = subtotal - descuento

    val costoEnvio = when {
        totalarticulos == 0 -> 0.0
        subtotalConDescuento >= 150.0 -> 0.0
        else -> 25.0
    }
    val totalFinal = subtotalConDescuento + costoEnvio

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(15.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "Tienda don pepe",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Color.Blue, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "$totalarticulos",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
        Spacer(Modifier.height(10.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            catalogo.forEachIndexed { index, producto ->
                ProductoItem(
                    producto = producto,
                    cantidad = cantidades[index],
                    onCantidadChange = {nuevacantidad -> cantidades[index]= nuevacantidad }
                )
                HorizontalDivider()
            }
        }
        ResumenCarrito(
            articulos = totalarticulos,
            subtotal = subtotal,
            descuento = descuento,
            envio = costoEnvio,
            total = totalFinal
        )

        Spacer(modifier = Modifier.height(15.dp))

        // Botones de acción inferiores
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(
                onClick = {
                    for (i in cantidades.indices) {
                        cantidades[i] = 0
                    }
                },
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
            ) {
                Text(text = "Vaciar", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
            }

            Button(
                onClick = {},
                enabled = totalarticulos > 0,
                modifier = Modifier
                    .weight(1.5f)
                    .height(50.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(containerColor =Color.Blue)
            ) {
                Text(text = "Confirmar pedido", fontSize = 15.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}
@Composable
fun ProductoItem(
    producto: Producto,
    cantidad: Int,
    onCantidadChange: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                producto.nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = String.format("$%.2f c/u", producto.precio),
                fontSize = 13.sp,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            OutlinedIconButton(
                onClick = { if (cantidad > 0) onCantidadChange(cantidad - 1) },
                enabled = cantidad > 0,
                modifier = Modifier.size(36.dp),
                shape = CircleShape
            ) {
                Text(
                    "-",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = "$cantidad",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            OutlinedIconButton(
                onClick = { if (cantidad > 10) onCantidadChange(cantidad + 1) },
                enabled = cantidad > 0,
                modifier = Modifier.size(36.dp),
                shape = CircleShape
            ) {
                Text(
                    "+",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }


        }

    }
}

@Composable
fun ResumenCarrito(
    articulos: Int,
    subtotal: Double,
    descuento: Double,
    envio: Double,
    total: Double
) {
    Card(
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFE5E4E5)),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Artículos", fontSize = 14.sp)
                Text(text = "$articulos", fontWeight = FontWeight.Bold, fontSize = 14.sp)
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Subtotal", fontSize = 14.sp)
                Text(
                    text = String.format("$%.2f", subtotal),
                    fontWeight = FontWeight.Bold, fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Descuento 10 %", fontSize = 14.sp)
                Text(
                    text = if (descuento > 0) String.format("-$%.2f", descuento) else "$0.00",
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Envío", fontSize = 14.sp)
                Text(
                    text = when {
                        articulos == 0 -> "$0.00"
                        envio == 0.0 -> "GRATIS"
                        else -> String.format("$%.2f", envio)
                    },
                    color = if (envio == 0.0 && articulos > 0) Color(0xFF2E7D32) else Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }

            HorizontalDivider()

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "TOTAL",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 18.sp,
                )
                Text(
                    text = String.format("$%.2f", total),
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 22.sp,
                    color = Color.Blue
                )
            }
        }
    }

}

@Preview
@Composable
fun AforoScreenPreview() {
    MutacionesTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            CarritoScreen(modifier = Modifier.padding(innerPadding))
        }
    }
}