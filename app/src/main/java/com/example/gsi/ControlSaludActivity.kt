package com.example.gsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gsi.databinding.ActivityControlSaludBinding
import com.example.gsi.databinding.ActivityMedicoAgregarBinding

class ControlSaludActivity : AppCompatActivity() {
    private lateinit var binding: ActivityControlSaludBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityControlSaludBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val enfermedad=intent.getStringExtra("enfermedad")
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
        binding.cardEnfermedades.setOnClickListener {
        val intent=Intent(this,EnfermedadPacienteActivity::class.java)
            intent.putExtra("enfermedad",enfermedad)
            startActivity(intent)
        }
    }
}