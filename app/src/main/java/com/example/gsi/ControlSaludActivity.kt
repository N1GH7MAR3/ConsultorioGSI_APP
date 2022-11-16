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
        val contactoemergencia=intent.getStringExtra("contactoemergencia")
        val contactomedico=intent.getStringExtra("contactomedico")
        val enfermedad=intent.getStringExtra("enfermedad")
        val medicina=intent.getStringExtra("medicina")
        val alergia=intent.getStringExtra("alergia")
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
        binding.cardContactoEmergencia.setOnClickListener {
            val intent=Intent(this,ContactoEmergenciaPacienteActivity::class.java)
            intent.putExtra("contactoemergencia",contactoemergencia)
            startActivity(intent)
        }
        binding.cardContactoMedico.setOnClickListener {
            val intent=Intent(this,ContactoMedicoPacienteActivity::class.java)
            intent.putExtra("contactomedico",contactomedico)
            startActivity(intent)
        }
        binding.cardEnfermedades.setOnClickListener {
        val intent=Intent(this,EnfermedadPacienteActivity::class.java)
            intent.putExtra("enfermedad",enfermedad)
            startActivity(intent)
        }
        binding.cardMedicina.setOnClickListener {
            val intent=Intent(this,MedicinaPacienteActivity::class.java)
            intent.putExtra("medicina",medicina)
            startActivity(intent)
        }
        binding.cardAlergias.setOnClickListener {
            val intent=Intent(this,AlergiaPacienteActivity::class.java)
            intent.putExtra("alergia",alergia)
            startActivity(intent)
        }
    }
}