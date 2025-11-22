package br.com.fiap.journey.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.journey.model.Usuario
import br.com.fiap.journey.repository.UsuarioRepository

@Composable
fun EditScreen(navController: NavController, usuarioId: Long){
    var nomeState = remember { mutableStateOf("") }
    var emailState = remember { mutableStateOf("") }
    var educationLevelState = remember { mutableStateOf("") }
    var areaDeTrabalhoState = remember { mutableStateOf("") }
    var senhaState = remember { mutableStateOf("") }

    val contexto = LocalContext.current
    val usuarioRepository = UsuarioRepository(contexto)


    LaunchedEffect(key1 = usuarioId) {
        val usuario = usuarioRepository.buscarPorId(usuarioId)
        if (usuario != null) {
            nomeState.value = usuario.nome
            emailState.value = usuario.email
            educationLevelState.value = usuario.educationLevel
            areaDeTrabalhoState.value = usuario.areaDeTrabalho
            senhaState.value = usuario.senha
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
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
                    text = "JOURNEY",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Card com os campos
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(40.dp),
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

                    EditForm(
                        id = usuarioId,
                        nome = nomeState.value,
                        email = emailState.value,
                        educationLevel = educationLevelState.value,
                        areaDeTrabalho = areaDeTrabalhoState.value,
                        senha = senhaState.value,
                        onNomeChange = { nomeState.value = it },
                        onEmailChange = { emailState.value = it },
                        onEducationLevelChange = { educationLevelState.value = it },
                        onAreaDeTrabalhoChange = { areaDeTrabalhoState.value = it },
                        onSenhaChange = { senhaState.value = it },
                        navController = navController
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                }
            }
            Spacer(modifier = Modifier.height(32.dp)) // Espaço no final para melhor rolagem
        }
    }
}

@Composable
fun EditForm(
    id:Long,
    nome: String,
    email: String,
    educationLevel: String,
    areaDeTrabalho: String,
    senha: String,
    onNomeChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onEducationLevelChange: (String) -> Unit,
    onAreaDeTrabalhoChange: (String) -> Unit,
    onSenhaChange: (String) -> Unit,
    navController: NavController
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
                id = id,
                nome = nome,
                email = email,
                educationLevel = educationLevel,
                areaDeTrabalho = areaDeTrabalho,
                senha = senha
            )
            usuarioRepository.atualizar(usuario)
            navController.navigate("login")
        },
        modifier = Modifier.fillMaxWidth().height(50.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF80DEEA)
        )
    ) {
        Text(
            text = "Atualizar seus dados",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0A0E27)
        )
    }
}
