package br.com.fiap.journey.screens
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
@Composable
fun AnswerSoftSkillsScreen(navController: NavController, usuarioId: Long) {
    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF0A0E27), Color(0xFF1A1F3A), Color(0xFF0A0E27))
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = { navController.popBackStack() }
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Text(text = "JOURNEY", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.White)
                IconButton(onClick = { navController.navigate("edit/${usuarioId}") }) {
                    Icon(Icons.Default.AccountCircle, contentDescription = "Editar", tint = Color.White, modifier = Modifier.size(32.dp))
                }
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Card(

                    shape = RoundedCornerShape(
                        topStart = 16.dp, topEnd = 16.dp,
                        bottomStart =  16.dp,
                        bottomEnd =  16.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor =  Color.White
                    ),
                    modifier = Modifier.fillMaxWidth(0.7f)
                        .align(Alignment.Start)
                ) {
                    Text(
                        text = "Seja bem-vindo ao Journey, como posso te ajudar hoje?",
                        modifier = Modifier.padding(12.dp),
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    shape = RoundedCornerShape(
                        topStart = 16.dp, topEnd = 16.dp,
                        bottomStart =  16.dp,
                        bottomEnd =  16.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor =  Color(0xFF80DEEA)
                    ),
                    modifier = Modifier.fillMaxWidth(0.7f)
                        .align(Alignment.End)
                ) {
                    Text(
                        text = "Gostaria de desenvolver minhas soft skills para avançar na carreira.",
                        modifier = Modifier.padding(12.dp),
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    shape = RoundedCornerShape(
                        topStart = 16.dp, topEnd = 16.dp,
                        bottomStart =  16.dp,
                        bottomEnd =  16.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor =  Color.White
                    ),
                    modifier = Modifier.fillMaxWidth(0.7f)
                        .align(Alignment.Start)
                ) {
                    Text(
                        text = "Excelente decisão! 🚀 As habilidades técnicas te abrem portas, mas são as soft skills que garantem sua promoção. Para que eu possa te indicar o treinamento mais eficaz, qual é a sua maior dificuldade no ambiente de trabalho hoje?",
                        modifier = Modifier.padding(12.dp),
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Card(
                    shape = RoundedCornerShape(
                        topStart = 16.dp, topEnd = 16.dp,
                        bottomStart =  16.dp,
                        bottomEnd =  16.dp
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor =  Color(0xFF80DEEA)
                    ),
                    modifier = Modifier.fillMaxWidth(0.7f)
                        .align(Alignment.End)
                ) {
                    Text(
                        text = "Eu sinto dificuldade em me organizar. Acabo perdendo prazos e isso me deixa estressado(a).",
                        modifier = Modifier.padding(12.dp),
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

            }

            Text("Journey digitando...", color = Color.Gray, fontSize = 12.sp, modifier = Modifier.padding(8.dp))

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
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Send,
                            contentDescription = "Enviar",
                            tint = if (searchText.isNotEmpty()) Color(0xFF0A0E27) else Color.Gray
                        )
                    }
                }
            )
        }
    }
}


