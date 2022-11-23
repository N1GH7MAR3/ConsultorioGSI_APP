package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.gsi.databinding.ActivityMedicoAgregarBinding

class MedicoAgregarActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMedicoAgregarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMedicoAgregarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        val nombre = intent.getStringExtra("nombre")
        val apePaterno = intent.getStringExtra("apePaterno")
        val apeMaterno = intent.getStringExtra("apeMaterno")
        val dni = intent.getStringExtra("dni")
       val especialidadid=intent.getStringExtra("especialidadid")

        if (!id.isNullOrEmpty()) {
            binding.textView3.text = "Editar Medico"
            binding.btnGuardar.text = "Editar Medico"
            binding.txtInputId.setText(id)
            binding.txtInputNombre.setText(nombre)
            binding.txtInputPaterno.setText(apePaterno)
            binding.txtInputMaterno.setText(apeMaterno)
            binding.txtInputDni.setText(dni)
        }else{
            binding.textView3.text = "Agregar Medico"
            binding.btnGuardar.text = "Agregar Medico"

        }

        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
    }
}