package com.example.gsi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gsi.Entity.Cita
import com.example.gsi.databinding.ActivityDashboardPacienteBinding

class DashboardPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dni = intent.getStringExtra("dni")
        val nombre = intent.getStringExtra("nombre")
        binding.txtNombre.text = "Hola, ${nombre}";
        binding.cardEspecialidades.setOnClickListener {
            val intent =
                Intent(this@DashboardPacienteActivity, EspecialidadesPacienteActivity::class.java)
            startActivity(intent)
        }
        binding.cardCitas.setOnClickListener {
            val intent = Intent(this@DashboardPacienteActivity, CitaPacienteActivity::class.java)
            intent.putExtra("dni", dni)
            startActivity(intent)
        }
        binding.cardCerrarSesion.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }



    }
}