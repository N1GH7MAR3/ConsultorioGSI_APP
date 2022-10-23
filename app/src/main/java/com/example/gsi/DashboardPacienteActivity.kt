package com.example.gsi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gsi.Service.ApiService
import com.example.gsi.Service.Services

class DashboardPacienteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_paciente)
        val nombre_paciente=findViewById<TextView>(R.id.txtNombre)
        val nombre = intent.getStringExtra("nombre")
        nombre_paciente.setText("Hola, $nombre")
        val boton=findViewById<ImageButton>(R.id.imageView22)
        boton.setOnClickListener{
            val intent = Intent(this@DashboardPacienteActivity, EspecialidadesActivity::class.java)
            startActivity(intent)
        }





    }
}