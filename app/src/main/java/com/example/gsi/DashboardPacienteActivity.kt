package com.example.gsi

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.gsi.Service.ApiService
import com.example.gsi.Service.Services
import com.example.gsi.databinding.ActivityDashboardPacienteBinding
import com.example.gsi.databinding.ActivityEspecialidadesBinding

class DashboardPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nombre = intent.getStringExtra("nombre")
        binding.txtNombre.text = "Hola, $nombre";
        binding.imageView22.setOnClickListener {
            val intent = Intent(this@DashboardPacienteActivity, EspecialidadesActivity::class.java)
            startActivity(intent)
        }


    }
}