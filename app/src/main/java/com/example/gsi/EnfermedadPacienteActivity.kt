package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.databinding.ActivityEnfermedadPacienteBinding

class EnfermedadPacienteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityEnfermedadPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityEnfermedadPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val enfermedad=intent.getStringExtra("enfermedad")
        binding.txtEnfermedad.text=enfermedad

        binding.btnRegresar.setOnClickListener {
            finish()
        }
    }
}