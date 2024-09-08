package com.example.tesouro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.tesouro.ui.theme.TesouroTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TesouroTheme {
                val navigationController = rememberNavController()
                NavHost(navController = navigationController, startDestination = "tela01") {
                    composable("tela01") {
                        Tela01(
                            onNavigateToScreen02 = { navigationController.navigate("tela02") }
                        )
                    }
                    composable("tela02") {
                        Tela02(
                            onNavigateToScreen03 = { navigationController.navigate("tela03") },
                            onNavigateToScreen01 = { navigationController.navigate("tela01") }
                        )
                    }
                    composable("tela03") {
                        Tela03(
                            onNavigateToScreen04 = { navigationController.navigate("tela04") },
                            onNavigateToScreen02 = { navigationController.navigate("tela02") }
                        )
                    }
                    composable("tela04") {
                        Tela04(
                            onRestart = { navigationController.navigate("tela01") }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Tela01(onNavigateToScreen02: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Bem-vindo à Caça ao Tesouro!", fontSize = 36.sp, fontFamily = FontFamily.Cursive)
        Button(
            onClick = { onNavigateToScreen02() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Green)
        ) {
            Text(text = "Iniciar Caça ao Tesouro")
        }
    }
}
@Composable
fun Tela02(onNavigateToScreen03: () -> Unit, onNavigateToScreen01: () -> Unit) {
    var selectedAnswer by remember { mutableStateOf("") }
    var showMessage by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Pista 1: O que é, o que é: Tem dentes, mas não come?", fontSize = 24.sp, fontFamily = FontFamily.Cursive)

        RadioButtonWithText(text = "Garfo", selectedAnswer, onSelect = { selectedAnswer = it })
        RadioButtonWithText(text = "Pente", selectedAnswer, onSelect = { selectedAnswer = it })
        RadioButtonWithText(text = "Serra", selectedAnswer, onSelect = { selectedAnswer = it })

        Button(
            onClick = {
                if (selectedAnswer == "Serra") {
                    onNavigateToScreen03()
                } else {
                    showMessage = true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
            enabled = selectedAnswer.isNotEmpty() // Habilita apenas se uma opção for selecionada
        ) {
            Text(text = "Próxima Pista")
        }
        Button(
            onClick = { onNavigateToScreen01() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Voltar")
        }

        if (showMessage) {
            Text(text = "Resposta errada! Tente novamente.", color = Color.Red, fontSize = 20.sp)
        }
    }
}

@Composable
fun Tela03(onNavigateToScreen04: () -> Unit, onNavigateToScreen02: () -> Unit) {
    var selectedAnswer by remember { mutableStateOf("") }
    var showMessage by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Pista 2: O que é, o que é: Tem asas, mas não voa?", fontSize = 24.sp, fontFamily = FontFamily.Cursive)

        RadioButtonWithText(text = "Avião", selectedAnswer, onSelect = { selectedAnswer = it })
        RadioButtonWithText(text = "Galinha", selectedAnswer, onSelect = { selectedAnswer = it })
        RadioButtonWithText(text = "Pinguim", selectedAnswer, onSelect = { selectedAnswer = it })

        Button(
            onClick = {
                if (selectedAnswer == "Pinguim") {
                    onNavigateToScreen04()
                } else {
                    showMessage = true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Yellow),
            enabled = selectedAnswer.isNotEmpty() // Habilita apenas se uma opção for selecionada
        ) {
            Text(text = "Próxima Pista")
        }
        Button(
            onClick = { onNavigateToScreen02() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text(text = "Voltar")
        }

        if (showMessage) {
            Text(text = "Resposta errada! Tente novamente.", color = Color.Red, fontSize = 20.sp)
        }
    }
}

@Composable
fun Tela04(onRestart: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Parabéns! Você encontrou o tesouro!", fontSize = 36.sp, fontFamily = FontFamily.Cursive)
        Button(
            onClick = { onRestart() },
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue)
        ) {
            Text(text = "Recomeçar")
        }
    }
}

@Composable
fun RadioButtonWithText(text: String, selectedAnswer: String, onSelect: (String) -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(
            selected = text == selectedAnswer,
            onClick = { onSelect(text) }
        )
        Text(text = text)
    }
}

@Preview(showBackground = true)
@Composable
fun MyPreview() {
    TesouroTheme {
        Tela01 {}
    }
}


