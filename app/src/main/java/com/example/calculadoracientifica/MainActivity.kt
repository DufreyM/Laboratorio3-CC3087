package com.example.calculadoracientifica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    // Declaración de la variable que representa la pantalla de la calculadora
    private lateinit var display: EditText
    // Instancia de la clase ScientificCalculator, que se encarga de evaluar las expresiones
    private val calculator = ScientificCalculator()

    // Método que se llama cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configura el diseño de la actividad a partir del archivo activity_main.xml
        setContentView(R.layout.activity_main)

        // Inicialización del EditText, que actuará como la pantalla de la calculadora
        display = findViewById(R.id.display)

        // Lista de IDs de los botones de la calculadora
        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
            R.id.buttonEquals, R.id.buttonClear, R.id.buttonSqrt, R.id.buttonPower,
            R.id.OpenParenthesis, R.id.CloseParenthesis
        )

        // Asigna un OnClickListener a cada botón de la lista
        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onButtonClick(it as Button) }
        }
    }

    // Método que se llama cuando se hace clic en un botón
    private fun onButtonClick(button: Button) {
        // Determina qué acción realizar según el ID del botón que se pulsó
        when (button.id) {
            // Si se pulsa el botón de igual
            R.id.buttonEquals -> {
                // Obtiene la expresión de la pantalla
                val expression = display.text.toString()
                try {
                    // Evalúa la expresión y muestra el resultado en la pantalla
                    val result = calculator.evaluate(expression)
                    display.setText(result.toString())
                } catch (e: Exception) {
                    // Si ocurre un error, muestra "Error" en la pantalla
                    display.setText("Error")
                }
            }
            // Si se pulsa el botón de borrar
            R.id.buttonClear -> {
                // Borra el contenido de la pantalla
                display.setText("")
            }
            // Para cualquier otro botón (números y operadores)
            else -> {
                // Añade el texto del botón a la pantalla
                display.append(button.text)
            }
        }
    }
}
