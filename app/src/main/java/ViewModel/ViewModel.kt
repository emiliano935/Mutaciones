package ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
//1
data class AforoUiState(
    val actuales: Int = 0,
    val visitasDia: Int = 0,
    val capacidadMax: Int = 50
) {
    val porcentaje: Float get() = actuales / capacidadMax.toFloat()
    val porciento: Int get() = (porcentaje * 100).toInt()
}

class AforoViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AforoUiState())
    val uiState: StateFlow<AforoUiState> = _uiState.asStateFlow()

    fun entra() {
        _uiState.update { s ->
            if (s.actuales < s.capacidadMax)
                s.copy(actuales = s.actuales + 1, visitasDia = s.visitasDia + 1)
            else s
        }
    }

    fun sale() {
        _uiState.update { s ->
            if (s.actuales > 0) s.copy(actuales = s.actuales - 1) else s
        }
    }

    fun cerrarDia() {
        _uiState.update { s -> s.copy(actuales = 0, visitasDia = 0) }
    }
}