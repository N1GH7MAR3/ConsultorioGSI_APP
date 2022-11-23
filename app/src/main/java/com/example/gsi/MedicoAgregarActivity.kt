package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.gsi.Constans.Constant
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
        val horarioid=intent.getStringExtra("horarioid")
        val paisid=intent.getStringExtra("paisid")
        val paisnombre=intent.getStringExtra("paisnombre")
        val sexoid=intent.getStringExtra("sexoid")
        val sexonombre=intent.getStringExtra("sexonombre")
        val turnoid=intent.getStringExtra("turnoid")
        val estadocivilid=intent.getStringExtra("estadocivilid")
        val estadocivilnombre=intent.getStringExtra("estadocivilnombre")



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
        Constant.api.getAllEspecialidades(binding,especialidadid!!)
        Constant.api.getAllPais(binding, paisid!!)
        Constant.api.getAllEstadoCivil(binding, estadocivilid!!)
        Constant.api.getAllTurno(binding,turnoid!!)
        Constant.api.getAllSexo(binding, sexoid!!)
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
    }
}