package ViewModel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

enum class Interlineado(val factor: Float) {
    COMPACTO(1.0f), NORMAL(1.3f), AMPLIO(1.6f)
}

data class LecturaUiState(
    val tamano: Float = 18f,
    val interlineado: Interlineado = Interlineado.NORMAL,
    val modoOscuro: Boolean = false,
    val usaSerif: Boolean = false
)

class LecturaViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(LecturaUiState())
    val uiState: StateFlow<LecturaUiState> = _uiState.asStateFlow()

    fun onTamanoChange(valor: Float) {
        _uiState.update { it.copy(tamano = valor) }
    }

    fun onInterlineadoChange(valor: Interlineado) {
        _uiState.update { it.copy(interlineado = valor) }
    }

    fun onModoOscuroChange(valor: Boolean) {
        _uiState.update { it.copy(modoOscuro = valor) }
    }

    fun onUsaSerifChange(valor: Boolean) {
        _uiState.update { it.copy(usaSerif = valor) }
    }
}