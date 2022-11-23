package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityEditarPerfilPacienteBinding
import com.example.gsi.databinding.ActivityPacientePerfilBinding

class EditarPerfilPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditarPerfilPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
        binding.btnCancelar.setOnClickListener {
            finish()
        }

    }
}