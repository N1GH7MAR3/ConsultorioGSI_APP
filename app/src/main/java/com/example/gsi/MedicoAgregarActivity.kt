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
        val pais = intent.getStringExtra("pais")
        val estadocivil = intent.getStringExtra("estadocivil")
        val turno = intent.getStringExtra("turno")
        val horarioingreso = intent.getStringExtra("horarioingreso")
        val horariosalida = intent.getStringExtra("horariosalida")
        val sexo = intent.getStringExtra("sexo")


        if (!id.isNullOrEmpty()) {
            binding.textView3.text = "Editar Medico"
            binding.btnGuardar.text = "Editar Medico"
           // binding.txtInputId.text = id
            //binding.txtLogin.isVisible = false
            //binding.txtInputId.setText(id)
            binding.txtInputNombre.setText(nombre)
            binding.txtInputPaterno.setText(apePaterno)
            binding.txtInputMaterno.setText(apeMaterno)
            binding.txtInputDni.setText(dni)
            //binding.txtInputEspecialidad.setText(nombre)
            //binding.txtInputHorario.setText(nombre)
           // binding.txtInputHorario.setText(nombre)
            //binding.txtInputHorario.setText(nombre)



        }


        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
    }
}