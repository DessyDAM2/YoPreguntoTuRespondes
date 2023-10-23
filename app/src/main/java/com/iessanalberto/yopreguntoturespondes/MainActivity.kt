package com.iessanalberto.yopreguntoturespondes

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.iessanalberto.yopreguntoturespondes.ui.theme.YoPreguntoTuRespondesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoPreguntoTuRespondesTheme {
                val context = LocalContext.current
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    PantallaJuego(context = context)
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable

    fun PantallaJuego(context: Context) {

        var puntuacion by rememberSaveable { mutableStateOf(0) }

        var pregunta by rememberSaveable {
            mutableStateOf(
                hashMapOf(
                    "¿Cuál es el juego más vendido de la historia?" to "Tetris",
                    "¿Cuál es el mamífero más pesado del mundo?" to "Ballena Azul",
                    "¿Cuál es el elemento químico con mayor densidad?" to "Osmio",
                    "¿Cuál es la velocidad del sonido en m/s?" to "344",
                    "¿Cuál es la capacidad siguiente al Terabyte?" to "Petabyte",
                ).keys.random()
            )
        }

        var respuesta by rememberSaveable {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(value = pregunta,
                onValueChange = { pregunta = it },
                label = { Text(text = "Pregunta") })


            OutlinedTextField(value = respuesta,
                onValueChange = { respuesta = it },
                label = { Text(text = "Respuesta") })

            OutlinedButton(onClick = {
                if (respuesta == hashMapOf(pregunta to respuesta)[pregunta]) {
                    puntuacion++
                    hashMapOf(pregunta to respuesta).remove(pregunta)
                } else {
                    Toast.makeText(
                        context,
                        "La respuesta correcta era " + hashMapOf(pregunta to respuesta)[pregunta],
                        Toast.LENGTH_LONG
                    ).show()
                }
                pregunta = hashMapOf(
                    "¿Cuál es el juego más vendido de la historia?" to "Tetris",
                    "¿Cuál es el mamífero más pesado del mundo?" to "Ballena Azul",
                    "¿Cuál es el elemento químico con mayor densidad?" to "Osmio",
                    "¿Cuál es la velocidad del sonido en m/s?" to "344",
                    "¿Cuál es la capacidad siguiente al Terabyte?" to "Petabyte",
                ).keys.random()
                respuesta = ""
            }
            ) {
                Text(text = "Validar Respuesta")
            }

            Text(text = "Puntuación: $puntuacion")
        }
    }

}



