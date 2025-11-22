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
fun AnswerNewOportunityScreen(navController: NavController, usuarioId: Long) {
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
                        text = "Tenho medo de perder meu emprego para a I.A. Como posso me preparar para uma nova oportunidade?",
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
                        text = "Entendo que o mercado está mudando rápido, e isso gera insegurança. O primeiro passo para uma transição segura é valorizar o que você já sabe. Para que eu possa te ajudar, qual é a sua profissão atual e o que você mais gosta de fazer nela?",
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
                        text = "Sou motorista de aplicativo. Gosto de dirigir e conhecer a cidade, mas tenho receio de que os carros autônomos acabem com meu emprego.",
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


