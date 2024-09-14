package com.example.calcularimc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.title = "App para calcular IMC"

        // Referencias a los elementos de la interfaz
        val estatura = findViewById<EditText>(R.id.etEstatura)
        val peso = findViewById<EditText>(R.id.etPeso)
        val btnCalcularIMC = findViewById<Button>(R.id.btnCalcularIMC)

        // Función para validar que los campos no estén vacíos
        fun validarCampos(): Boolean {
            if(estatura.text.isEmpty()){
                estatura.error = "Campo Requerido"
            }else if (peso.text.isEmpty()) {
                peso.error = "Campo Requerido"
            }else {
                return true
            }
            return false
        }

        // Función para calcular el IMC
        fun calcularIMC(peso: Double, estatura: Double): Double {
            return peso / (estatura * estatura)
        }

        // Función para obtener el mensaje de rango del IMC
        fun obtenerMensajeIMC(imc: Double): String {
            when {
                imc <= 15 -> return "Delgadez muy severa"
                imc in 15.1..15.9 -> return "Delgadez severa"
                imc in 16.0..18.4 -> return "Delgadez"
                imc in 18.5..24.9 -> return "Peso saludable"
                imc in 25.0..29.9 -> return "Sobrepeso"
                imc in 30.0..34.9 -> return "Obesidad moderada"
                imc in 35.0..39.9 -> return "Obesidad severa"
                else -> return "Obesidad muy severa (obesidad mórbida)"
            }
        }

        // Evento click para el botón "Calcular IMC"
        btnCalcularIMC.setOnClickListener {
            if (validarCampos()) {
                val estaturaToast = estatura.text.toString().toDouble()
                val pesoToast = peso.text.toString().toDouble()

                val imc = calcularIMC(pesoToast, estaturaToast)
                val mensajeIMC = obtenerMensajeIMC(imc)

                Toast.makeText(this, "IMC: %.2f: %s".format(imc, mensajeIMC), Toast.LENGTH_LONG).show()
            }
        }
    }
}
