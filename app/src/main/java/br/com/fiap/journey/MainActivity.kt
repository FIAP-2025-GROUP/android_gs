package br.com.fiap.journey


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.journey.screens.AnswerHardSkillsScreen
import br.com.fiap.journey.screens.AnswerNewOportunityScreen
import br.com.fiap.journey.screens.AnswerSoftSkillsScreen
import br.com.fiap.journey.screens.AnswerUseAiScreen
import br.com.fiap.journey.screens.EditScreen
import br.com.fiap.journey.screens.LearnScreen
import br.com.fiap.journey.screens.LoginScreen
import br.com.fiap.journey.screens.RegisterScreen
import br.com.fiap.journey.ui.theme.JourneyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JourneyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {

                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    )
                    {
                        composable("login") {
                            LoginScreen(navController)
                        }
                        composable("register") {
                            RegisterScreen(navController)
                        }
                        composable("forgot") {
                            LearnScreen(navController)
                        }
                        composable(
                            "learn/{usuarioId}",
                            arguments = listOf(navArgument("usuarioId") { type = NavType.LongType })
                        ) {
                            val usuarioId = it.arguments?.getLong("usuarioId") ?: 0L
                            LearnScreen(navController, usuarioId = usuarioId)
                        }

                        composable(
                            "answerHardSkills/{usuarioId}",
                            arguments = listOf(navArgument("usuarioId") { type = NavType.LongType })
                        ) {

                            val usuarioId = it.arguments?.getLong("usuarioId") ?: 0L
                            AnswerHardSkillsScreen(navController, usuarioId = usuarioId)
                        }
                        composable(
                            "answerSoftSkills/{usuarioId}",
                            arguments = listOf(navArgument("usuarioId") { type = NavType.LongType })
                        ) {

                            val usuarioId = it.arguments?.getLong("usuarioId") ?: 0L
                            AnswerSoftSkillsScreen(navController, usuarioId = usuarioId)
                        }
                        composable(
                            "answerUseAi/{usuarioId}",
                            arguments = listOf(navArgument("usuarioId") { type = NavType.LongType })
                        ) {

                            val usuarioId = it.arguments?.getLong("usuarioId") ?: 0L
                            AnswerUseAiScreen(navController, usuarioId = usuarioId)
                        }
                        composable(
                            "answerNewOportunity/{usuarioId}",
                            arguments = listOf(navArgument("usuarioId") { type = NavType.LongType })
                        ) {

                            val usuarioId = it.arguments?.getLong("usuarioId") ?: 0L
                            AnswerNewOportunityScreen(navController, usuarioId = usuarioId)
                        }

                        composable(
                            "edit/{usuarioId}",
                            arguments = listOf(navArgument("usuarioId"){type = NavType.LongType})
                        ){
                            val usuarioId = it.arguments?.getLong("usuarioId") ?: 0L
                            EditScreen(navController, usuarioId = usuarioId)
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {

}
