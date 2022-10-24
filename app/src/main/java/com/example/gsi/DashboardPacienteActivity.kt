package com.example.gsi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gsi.databinding.ActivityDashboardPacienteBinding

class DashboardPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nombre = intent.getStringExtra("nombre")
        binding.txtNombre.text = "Hola, $nombre";
        binding.imageView22.setOnClickListener {
            val intent = Intent(this@DashboardPacienteActivity, EspecialidadesPacienteActivity::class.java)
            startActivity(intent)

        }


    }
}