package br.com.fiap.journey.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.journey.model.Usuario
import br.com.fiap.journey.repository.UsuarioRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController) {

    var nomeState = remember { mutableStateOf("") }
    var emailState = remember { mutableStateOf("") }
    var educationLevelState = remember { mutableStateOf("") }
    var areaDeTrabalhoState = remember { mutableStateOf("") }
    var senhaState = remember { mutableStateOf("") }

    /*val levelsEducacao = listOf(
        "Nenhuma escolaridade / Analfabeto",
        "Ensino Fundamental Incompleto",
        "Ensino Fundamental Completo",
        "Ensino Médio Incompleto",
        "Ensino Médio Completo",
        "Curso Técnico",
        "Graduação (Superior) Incompleta",
        "Graduação (Superior) Completa",
        "Pós-Graduação Lato Sensu (Especialização)",
        "Pós-Graduação Stricto Sensu — Mestrado",
        "Pós-Graduação Stricto Sensu — Doutorado"
    )*/

    Box(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0A0E27),
                        Color(0xFF1A1F3A),
                        Color(0xFF0A0E27)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {

            IconButton(

                onClick = { navController.popBackStack() }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Voltar",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

            // Título
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Start your ")
                        withStyle(style = SpanStyle(color = Color(0xFF80DEEA))) {
                            append("journey")
                        }
                    },
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "toward the",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "FUTURE HERE!",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF80DEEA),
                    textAlign = TextAlign.Center,
                    letterSpacing = 2.sp
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Card com os campos
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE8E8E8)
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "👨‍🚀",
                        fontSize = 48.sp,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    RegisterForm(
                        nome = nomeState.value,
                        email = emailState.value,
                        educationLevel = educationLevelState.value,
                        areaDeTrabalho = areaDeTrabalhoState.value,
                        senha = senhaState.value,
                        onNomeChange = { nomeState.value = it },
                        onEmailChange = { emailState.value = it },
                        onEducationLevelChange = { educationLevelState.value = it },
                        onAreaDeTrabalhoChange = { areaDeTrabalhoState.value = it },
                        onSenhaChange = { senhaState.value = it }
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                }
            }
            Spacer(modifier = Modifier.height(32.dp)) // Espaço no final para melhor rolagem
        }
    }
}

@Composable
fun RegisterForm(
    nome: String,
    email: String,
    educationLevel: String,
    areaDeTrabalho: String,
    senha: String,
    onNomeChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onEducationLevelChange: (String) -> Unit,
    onAreaDeTrabalhoChange: (String) -> Unit,
    onSenhaChange: (String) -> Unit
){
    val contexto = LocalContext.current
    val usuarioRepository = UsuarioRepository(contexto)


    // Campo Nome
    OutlinedTextField(
        value = nome,
        onValueChange = { onNomeChange(it) },
        placeholder = { Text("Nome", color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(30.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF80DEEA),
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = Color(0xFF00BCD4)
        ),
        singleLine = true
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Campo Email
    OutlinedTextField(
        value = email,
        onValueChange = { onEmailChange(it) },
        placeholder = { Text("Email", color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(30.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF80DEEA),
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = Color(0xFF00BCD4)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Campo Education Level
    OutlinedTextField(
        value = educationLevel,
        onValueChange = { onEducationLevelChange(it) },
        placeholder = { Text("Nível de educação", color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(30.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF80DEEA),
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = Color(0xFF00BCD4)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Campo Work Area
    OutlinedTextField(
        value = areaDeTrabalho,
        onValueChange = { onAreaDeTrabalhoChange(it) },
        placeholder = { Text("Área de atuação", color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(30.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF80DEEA),
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = Color(0xFF00BCD4)
        ),
        singleLine = true
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Campo Password
    OutlinedTextField(
        value = senha,
        onValueChange = { onSenhaChange(it) },
        placeholder = { Text("Senha", color = Color.Gray) },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        shape = RoundedCornerShape(30.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF80DEEA),
            unfocusedBorderColor = Color.Transparent,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            cursorColor = Color(0xFF00BCD4)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true
    )

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = {
            val usuario = Usuario(
                nome = nome,
                email = email,
                educationLevel = educationLevel,
                areaDeTrabalho = areaDeTrabalho,
                senha = senha
            )
            usuarioRepository.salvar(usuario = usuario)

            onNomeChange("")
            onEmailChange("")
            onEducationLevelChange("")
            onAreaDeTrabalhoChange("")
            onSenhaChange("")
        },
        modifier = Modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF80DEEA))
    ) {
        Text(text = "CRIAR CONTA", color = Color.Black, fontWeight = FontWeight.Bold)
    }
}
