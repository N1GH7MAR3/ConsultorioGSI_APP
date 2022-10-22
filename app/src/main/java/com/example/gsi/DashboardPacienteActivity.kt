package com.example.gsi

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardPacienteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_paciente)
        val nombre_paciente=findViewById<TextView>(R.id.txtNombre)
        val nombre = intent.getStringExtra("nombre")
        nombre_paciente.setText("Hola, $nombre")




    }
}