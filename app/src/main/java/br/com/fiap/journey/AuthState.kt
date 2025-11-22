package br.com.fiap.journey

sealed class AuthState {
    object IDLE : AuthState()
    object LOADING : AuthState()
    data class SUCCESS(val userId: Long) : AuthState() // Agora o sucesso carrega o ID
    object FAILURE : AuthState()
}
