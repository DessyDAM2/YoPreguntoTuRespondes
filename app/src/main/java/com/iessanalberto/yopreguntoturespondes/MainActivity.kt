package com.iessanalberto.yopreguntoturespondes

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.iessanalberto.yopreguntoturespondes.ui.theme.YoPreguntoTuRespondesTheme

class MainActivity : ComponentActivity() {

    var listaPreguntas = mutableListOf(
        "¿Cuál es el videojuego más vendido de la historia?",
        "¿Cuál es el mamífero más pesado del mundo?",
        "¿Cuál es el elemento químico con mayor densidad?",
        "¿Cuál es la velocidad del sonido en m/s?",
        "¿Cuál es la capacidad siguiente al Terabyte?"
    )

    var listaRespuestas = mutableListOf(
        "Tetris",
        "Ballena Azul",
        "Osmio",
        "343",
        "Petabyte"
    )

    val listaPreguntas2 = mutableListOf(
        "¿Cuál es el videojuego más vendido de la historia?",
        "¿Cuál es el mamífero más pesado del mundo?",
        "¿Cuál es el elemento químico con mayor densidad?",
        "¿Cuál es la velocidad del sonido en m/s?",
        "¿Cuál es la capacidad siguiente al Terabyte?"
    )

    val listaRespuestas2 = mutableListOf(
        "Tetris",
        "Ballena Azul",
        "Osmio",
        "343",
        "Petabyte"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            YoPreguntoTuRespondesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    PantallaJuego()
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun PantallaJuego() {

        val context = LocalContext.current

        var maxPreguntas by rememberSaveable {
            mutableStateOf(0)
        }

        var puntuacion by rememberSaveable {
            mutableStateOf(0)
        }

        var pregunta by rememberSaveable {
            mutableStateOf(listaPreguntas.random())
        }

        var respuesta by rememberSaveable {
            mutableStateOf(listaRespuestas[listaPreguntas.indexOf(pregunta)])
        }

        var respuestaUsuario by rememberSaveable {
            mutableStateOf("")
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Box(modifier = Modifier.padding(16.dp)) {
                Image(painterResource(id = R.drawable.trivia), contentDescription = "")
            }

            BasicTextField(
                value = pregunta,
                onValueChange = { pregunta = it },
                modifier = Modifier.padding(16.dp),
                textStyle = MaterialTheme.typography.titleMedium,
                singleLine = true
            )

            TextField(
                value = respuestaUsuario,
                onValueChange = { respuestaUsuario = it },
                label = { Text(text = "Respuesta") },
                modifier = Modifier.padding(16.dp)
            )


            Button(onClick = {
                if (respuesta == respuestaUsuario) {
                    listaPreguntas.remove(pregunta)
                    listaRespuestas.remove(respuesta)
                    puntuacion++
                    maxPreguntas++
                    pregunta = listaPreguntas.random()
                    respuesta = listaRespuestas[listaPreguntas.indexOf(pregunta)]
                    Toast.makeText(context, "Respuesta correcta", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(context, "Respuesta incorrecta", Toast.LENGTH_LONG).show()
                }

                respuestaUsuario = ""

                if (maxPreguntas == 3) {
                    listaPreguntas = listaPreguntas2
                    listaRespuestas = listaRespuestas2
                    maxPreguntas = 0
                    puntuacion = 0
                    pregunta = listaPreguntas.random()
                    respuesta = listaRespuestas[listaPreguntas.indexOf(pregunta)]
                    Toast.makeText(context, "Juego reiniciado", Toast.LENGTH_LONG).show()
                }
            }

            ) {
                Text(text = "Validar Respuesta")
            }

            Text(text = "Puntuación: $puntuacion")
        }
    }
}





