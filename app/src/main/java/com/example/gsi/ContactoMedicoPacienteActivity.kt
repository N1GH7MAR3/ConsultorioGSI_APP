package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.databinding.ActivityContactoMedicoPacienteBinding

class ContactoMedicoPacienteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityContactoMedicoPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityContactoMedicoPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contactomedico=intent.getStringExtra("contactomedico")
        binding.txtContactoMedico.text=contactomedico

        binding.btnRegresar.setOnClickListener {
            finish()
        }
    }
}