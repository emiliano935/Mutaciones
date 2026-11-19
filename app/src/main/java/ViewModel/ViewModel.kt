package ViewModel

import androidx.lifecycle.ViewModel
import androidx.room.util.copy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Producto(val nombre: String, val precio: Double)

data class carritoestado(
    val catalogo: List<Producto> = listOf(
        Producto("Tortillas 1 kg", 24.00),
        Producto("Leche 1 L", 27.50),
        Producto("Huevo 12 pz", 52.00),
        Producto("Pan 1 pz", 38.00)
    ),
    val cantidades: List<Int> = listOf(0, 0, 0, 0)
) {
    val totalarticulos: Int
        get() = cantidades.sum()

    val subtotal: Double
        get() = catalogo.indices.sumOf { i -> catalogo[i].precio * cantidades[i] }

    val descuento: Double
        get() = if (subtotal >= 200.0) subtotal * 0.10 else 0.0

    val subtotalConDescuento: Double
        get() = subtotal - descuento

    val costoEnvio: Double
        get() = when {
            totalarticulos == 0 -> 0.0
            subtotalConDescuento >= 150.0 -> 0.0
            else -> 25.0
        }

    val totalFinal: Double
        get() = subtotalConDescuento + costoEnvio
}

class CarritoViewModel : ViewModel() {
    private val  uiestado1= MutableStateFlow(carritoestado())
    val UIestado : StateFlow<carritoestado> = uiestado1.asStateFlow()

    fun cambiarCantidad(index: Int, nuevaCantidad: Int) {
        uiestado1.update { s ->
            s.copy(
                cantidades = s.cantidades.toMutableList().also { it[index] = nuevaCantidad }
            )
        }
    }

    fun vaciar() {
        uiestado1.update { s -> s.copy(cantidades = s.cantidades.map { 0 }) }
    }
}