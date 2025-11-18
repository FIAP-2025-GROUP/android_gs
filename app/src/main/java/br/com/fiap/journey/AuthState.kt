package br.com.fiap.journey

import android.app.Application
import android.widget.Toast
import androidx.activity.result.launch
import androidx.compose.ui.semantics.password
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import br.com.fiap.journey.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

enum class AuthState {
    IDLE,       // Estado inicial
    LOADING,    // Autenticando
    SUCCESS,    // Sucesso
    FAILURE     // Falha
}

