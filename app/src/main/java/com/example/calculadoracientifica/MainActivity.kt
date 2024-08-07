package com.example.calculadoracientifica

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import java.util.Stack
import kotlin.math.*

class MainActivity : AppCompatActivity() {

    private lateinit var display: EditText
    private val calculator = ScientificCalculator()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización del EditText
        display = findViewById(R.id.display)

        val buttons = listOf(
            R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4,
            R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9,
            R.id.buttonAdd, R.id.buttonSubtract, R.id.buttonMultiply, R.id.buttonDivide,
            R.id.buttonEquals, R.id.buttonClear, R.id.buttonSqrt, R.id.buttonPower
        )

        buttons.forEach { id ->
            findViewById<Button>(id).setOnClickListener { onButtonClick(it as Button) }
        }
    }

    private fun onButtonClick(button: Button) {
        when (button.id) {
            R.id.buttonEquals -> {
                val expression = display.text.toString()
                try {
                    val result = calculator.evaluate(expression)
                    display.setText(result.toString())
                } catch (e: Exception) {
                    display.setText("Error")
                }
            }
            R.id.buttonClear -> {
                display.setText("")
            }
            else -> {
                display.append(button.text)
            }
        }
    }
}

