package ViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class Inscripcioninterfazstate(
    val nombre: String = "",
    val correo: String = "",
    val edad: String = "",
    val taller: String = "Fotografía",
    val aceptaAviso: Boolean = false,
    val intentoEnviar: Boolean = false,
    val inscrito: Boolean = false
) {
    val errorNombre: String?
        get() = if (intentoEnviar && nombre.length < 3)
            "El nombre debe tener al menos 3 caracteres" else null

    val errorCorreo: String?
        get() = if (intentoEnviar &&
            (!correo.contains("@") || !correo.substringAfter("@").contains("."))
        )
            "Ingresa un correo válido" else null

    val errorEdad: String?
        get() {
            val e = edad.toIntOrNull()
            return if (intentoEnviar && (e == null || e !in 12..99))
                "La edad debe estar entre 12 y 99" else null
        }

    val errorAviso: String?
        get() = if (intentoEnviar && !aceptaAviso)
            "Debes aceptar el aviso de privacidad" else null

    val formularioValido: Boolean
        get() = nombre.length >= 3 &&
                correo.contains("@") && correo.substringAfter("@").contains(".") &&
                (edad.toIntOrNull() ?: 0) in 12..99 &&
                aceptaAviso
}

class inscripcionviewMoldel : ViewModel() {
    private val UInterfaz= MutableStateFlow(Inscripcioninterfazstate())
    val UIestado: StateFlow<Inscripcioninterfazstate> =UInterfaz.asStateFlow()

    fun onNombreChange(valor: String) {
        UInterfaz.update { it.copy(nombre = valor) }
    }

    fun onCorreoChange(valor: String) {
        UInterfaz.update { it.copy(correo = valor) }
    }

    fun onEdadChange(valor: String) {
        // Solo permite dígitos
        if (valor.all { it.isDigit() }) {
            UInterfaz.update { it.copy(edad = valor) }
        }
    }

    fun onTallerChange(valor: String) {
        UInterfaz.update { it.copy(taller = valor) }
    }

    fun onAceptaAvisoChange(valor: Boolean) {
        UInterfaz.update { it.copy(aceptaAviso = valor) }
    }

    fun onSubmit() {
        UInterfaz.update { s ->
            s.copy(
                intentoEnviar = true,
                inscrito = s.formularioValido
            )
        }
    }

    fun onNueva() {
        UInterfaz.value = Inscripcioninterfazstate()
    }
}
