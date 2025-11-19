package br.com.fiap.journey.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import br.com.fiap.journey.LoginViewModel
import br.com.fiap.journey.R
import br.com.fiap.journey.AuthState

@Composable
fun LoginScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {

    var emailState by remember { mutableStateOf("") }
    var senhaState by remember { mutableStateOf("") }

    val authState by loginViewModel.authState.collectAsState()

    LaunchedEffect(authState) {
        if (authState == AuthState.SUCCESS) {
            navController.navigate("learn") {
                popUpTo(navController.graph.startDestinationId) {
                    inclusive = true
                }
            }
            loginViewModel.resetAuthState()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
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
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            AstronautSection()
            Spacer(modifier = Modifier.height(32.dp))
            Text(text = "Your JOURNEY", fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.White, textAlign = TextAlign.Center)
            Text(text = "starts here", fontSize = 28.sp, fontWeight = FontWeight.Light, color = Color.White, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(40.dp))

            LoginForm(
                email = emailState,
                senha = senhaState,
                onEmailChange = { emailState = it },
                onSenhaChange = { senhaState = it }
            )

            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.End)) {
                Text(text = "Forgot password?", color = Color.White, fontSize = 14.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    loginViewModel.login(emailState, senhaState)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF0277BD)
                ),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 8.dp,
                    pressedElevation = 12.dp
                ),
                // Use a referência direta para o SEU enum
                enabled = authState != AuthState.LOADING
            ) {
                // E aqui também
                if (authState == AuthState.LOADING) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Entrar",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "or", color = Color.White.copy(alpha = 0.6f), fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedButton(
                onClick = { navController.navigate("register") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(28.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color(0xFF80DEEA)),
                border = ButtonDefaults.outlinedButtonBorder.copy(
                    width = 2.dp,
                    brush = Brush.horizontalGradient(colors = listOf(Color(0xFF80DEEA), Color(0xFF00BCD4)))
                )
            ) {
                Text(text = "New account", fontSize = 18.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

// O restante do seu código (LoginForm, AstronautSection, etc.) permanece o mesmo e está correto.
@Composable
fun LoginForm(
    email: String,
    onEmailChange: (String) -> Unit,
    senha: String,
    onSenhaChange: (String) -> Unit
){
    OutlinedTextField(
        value = email,
        onValueChange = { onEmailChange(it) },
        label = { Text("Email", color = Color.White.copy(alpha = 0.7f)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Email,
                contentDescription = "Email",
                tint = Color(0xFF00BCD4)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        shape = RoundedCornerShape(32.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = Color(0xFF00BCD4),
            unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
            cursorColor = Color(0xFF00BCD4),
            focusedContainerColor = Color.White.copy(alpha = 0.1f),
            unfocusedContainerColor = Color.White.copy(alpha = 0.05f)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        singleLine = true
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = senha,
        onValueChange = { onSenhaChange(it) },
        label = { Text("Senha", color = Color.White.copy(alpha = 0.7f)) },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Senha",
                tint = Color(0xFF00BCD4)
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp),
        shape = RoundedCornerShape(32.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedBorderColor = Color(0xFF00BCD4),
            unfocusedBorderColor = Color.White.copy(alpha = 0.5f),
            cursorColor = Color(0xFF00BCD4),
            focusedContainerColor = Color.White.copy(alpha = 0.1f),
            unfocusedContainerColor = Color.White.copy(alpha = 0.05f)
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true
    )
}

@Composable
fun AstronautSection() {
    Box(
        modifier = Modifier
            .size(200.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_journey),
                contentDescription = "Astronaut on rocket",
                modifier = Modifier.size(180.dp)
            )
            Text(
                text = "Learning toward the future",
                color = Color.White.copy(alpha = 0.8f),
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun SpaceBackground(alpha: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .alpha(alpha * 0.3f)
    ) { }
}
