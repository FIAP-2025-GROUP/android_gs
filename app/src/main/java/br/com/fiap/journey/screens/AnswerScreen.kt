package br.com.fiap.journey.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

// Certifique-se de importar o ChatRequest corretamente do pacote onde ele está (provavelmente no mesmo pacote)
// Se der erro de importação, verifique se ChatRequest está no arquivo JourneyApi.kt

// Modelo de mensagem local
data class ChatMessage(
    val text: String,
    val isUser: Boolean
)

@Composable
fun AnswerScreen(navController: NavController) {
    var searchText by remember { mutableStateOf("") }

    // Adicionei uma mensagem inicial padrão do Journey
    val messages = remember {
        mutableStateListOf(
            ChatMessage(text = "Olá! Sou o Journey. Como posso ajudar na sua carreira hoje?", isUser = false)
        )
    }

    // Escopo para executar tarefas de rede
    val scope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }

    // Função que conecta com a API Python
    fun sendMessage() {
        if (searchText.isNotBlank()) {
            val userMessage = searchText
            // 1. Adiciona mensagem do usuário na tela
            messages.add(ChatMessage(text = userMessage, isUser = true))
            searchText = ""
            isLoading = true

            scope.launch {
                try {
                    // CORREÇÃO AQUI:
                    // O ChatRequest agora exige 3 parâmetros.
                    // Como essa tela é genérica, passamos valores padrão.
                    val request = ChatRequest(
                        msg = userMessage,
                        topic = "geral",         // Valor padrão para não dar erro
                        user_education = 5       // Valor padrão (Nível Técnico)
                    )

                    // 2. Chama a API no Python
                    val response = RetrofitClient.instance.sendMessage(request)

                    // 3. Adiciona a resposta do bot na tela
                    messages.add(ChatMessage(text = response, isUser = false))
                } catch (e: Exception) {
                    // Tratamento de erro
                    messages.add(ChatMessage(text = "Erro ao conectar: ${e.message}", isUser = false))
                    e.printStackTrace()
                } finally {
                    isLoading = false
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0A0E27), Color(0xFF1A1F3A), Color(0xFF0A0E27))
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Cabeçalho
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "JOURNEY", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.White)
                IconButton(onClick = { navController.navigate("edit/${1L}") }) {
                    Icon(Icons.Default.AccountCircle, contentDescription = "Editar", tint = Color.White, modifier = Modifier.size(32.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de mensagens
            LazyColumn(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                reverseLayout = true
            ) {
                items(messages.reversed()) { message ->
                    MessageBubble(message = message)
                }
            }

            if (isLoading) {
                Text("Journey digitando...", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(8.dp))
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de Input
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("Digite sua dúvida...", color = Color.Gray) }, // Texto corrigido
                modifier = Modifier.fillMaxWidth().height(60.dp),
                shape = RoundedCornerShape(30.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF80DEEA),
                    unfocusedBorderColor = Color.Transparent,
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    cursorColor = Color(0xFF00BCD4)
                ),
                singleLine = true,
                trailingIcon = {
                    IconButton(onClick = { if (!isLoading) sendMessage() }) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Enviar",
                            tint = if (searchText.isNotEmpty()) Color(0xFF0A0E27) else Color.Gray
                        )
                    }
                }
            )
        }
    }
}

@Composable
fun MessageBubble(message: ChatMessage) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (message.isUser) Alignment.End else Alignment.Start
    ) {
        Card(
            shape = RoundedCornerShape(
                topStart = 16.dp, topEnd = 16.dp,
                bottomStart = if (message.isUser) 16.dp else 0.dp,
                bottomEnd = if (message.isUser) 0.dp else 16.dp
            ),
            colors = CardDefaults.cardColors(
                containerColor = if (message.isUser) Color(0xFF80DEEA) else Color.White
            ),
            modifier = Modifier.widthIn(max = 300.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                color = Color.Black
            )
        }
    }
}