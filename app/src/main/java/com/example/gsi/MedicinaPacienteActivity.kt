package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.example.gsi.databinding.ActivityMedicinaPacienteBinding


class MedicinaPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicinaPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMedicinaPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val medicina=intent.getStringExtra("medicina")
        binding.txtMedicina.text=medicina

        binding.btnRegresar.setOnClickListener {
            finish()
        }
    }
}