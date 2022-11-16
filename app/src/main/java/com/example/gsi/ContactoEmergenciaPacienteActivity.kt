package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.databinding.ActivityContactoEmergenciaPacienteBinding

class ContactoEmergenciaPacienteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityContactoEmergenciaPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityContactoEmergenciaPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val contactoemergencia=intent.getStringExtra("contactoemergencia")
        binding.txtContactoEmergencia.text=contactoemergencia

        binding.btnRegresar.setOnClickListener {
            finish()
        }
    }
}