package br.com.fiap.journey

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.journey.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UsuarioRepository(application)

    // O tipo do StateFlow agora é a sealed class
    private val _authState = MutableStateFlow<AuthState>(AuthState.IDLE)
    val authState: StateFlow<AuthState> = _authState

    fun login(email: String, senha: String) {
        if (email.isEmpty() || senha.isEmpty()) {
            Toast.makeText(
                getApplication(),
                "Por favor, preencha todos os campos.",
                Toast.LENGTH_SHORT
            ).show()
            return
        }

        viewModelScope.launch {
            _authState.value = AuthState.LOADING

            val user = repository.buscarPorEmail(email)

            // Se o usuário for encontrado e a senha bater...
            if (user != null && user.senha == senha) {
                // ...emita o estado de SUCESSO com o ID do usuário.
                _authState.value = AuthState.SUCCESS(user.id)
            } else {
                _authState.value = AuthState.FAILURE
                Toast.makeText(getApplication(), "Email ou senha inválidos.", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    fun resetAuthState() {
        _authState.value = AuthState.IDLE
    }
}