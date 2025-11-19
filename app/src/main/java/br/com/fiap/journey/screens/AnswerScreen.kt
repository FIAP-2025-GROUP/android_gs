package br.com.fiap.journey.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
// Importações necessárias
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue // Importar getValue
import androidx.compose.runtime.mutableStateOf // Importar mutableStateOf
import androidx.compose.runtime.remember // Importar remember
import androidx.compose.runtime.setValue // Importar setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import br.com.fiap.journey.R

@Composable
fun AnswerScreen(navController: NavController){

    var searchText by remember { mutableStateOf("") }
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                // Título
                Text(
                    text = "JOURNEY",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                IconButton(
                    onClick = {navController.navigate("edit")}
                ){
                    Icon(
                        imageVector = Icons.Default.AccountCircle,
                        contentDescription = "Editar",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(300.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
            ){
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque auctor erat quis arcu tincidunt, eu faucibus eros aliquet. Phasellus ornare enim orci, mollis facilisis nunc iaculis tristique. Etiam sit amet semper erat.",
                    modifier = Modifier.padding(16.dp)
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .height(150.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)

            ){
                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque auctor erat quis arcu tincidunt, eu faucibus eros aliquet.",
                    modifier = Modifier.padding(16.dp)
                )
            }
            OutlinedTextField(
                value = searchText,
                onValueChange = { searchText = it },
                placeholder = { Text("O que você quer perguntar?", color = Color.Gray) },
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
                singleLine = true,

                trailingIcon = {
                    if (searchText.isNotEmpty()){
                        IconButton(
                            onClick = { searchText = "" }
                        ){
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Limpar busca",
                                tint = Color.Gray
                            )
                        }
                    }else {
                        Icon(
                            painter = painterResource(R.drawable.logo_journey),
                            contentDescription = "Buscar",
                            tint = Color.Gray
                        )
                    }
                }
            )
        }
    }
}
