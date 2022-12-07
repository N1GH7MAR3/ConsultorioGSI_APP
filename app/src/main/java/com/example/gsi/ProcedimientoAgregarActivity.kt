package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.*
import com.example.gsi.databinding.ActivityProcedimientoAgregarBinding

class ProcedimientoAgregarActivity : AppCompatActivity() {
    private lateinit var binding:ActivityProcedimientoAgregarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityProcedimientoAgregarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val id = intent.getStringExtra("id")
        val nombre = intent.getStringExtra("nombre")
        val especialidadid=intent.getStringExtra("especialidadid")

        if (!id.isNullOrEmpty()) {
            binding.textView3.text = "Editar Procedimiento"
            binding.btnGuardar.text = "Editar Procedimiento"
            binding.txtInputId.setText(id)
            binding.txtInputNombre.setText(nombre)
            binding.spEspecialidad.isEnabled=false

        }else{
            binding.textView3.text = "Agregar Procedimiento"
            binding.btnGuardar.text = "Agregar Procedimiento"
            binding.txtLayautId.isVisible=false
            binding.txtInputId.isVisible=false
        }
        Constant.api.getAllEspecialidades(binding, especialidadid.toString())
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
        binding.btnGuardar.setOnClickListener {
            val especialidad= putEspecialidad(binding.spEspecialidad.selectedItemId)
            val procedimiento= createProcedimiento(binding.txtInputNombre.text.toString(), especialidad)
            if(id.toString() =="null"){
                Constant.api.createProcedimiento(procedimiento,binding)
            }else{
                Constant.api.updateProcedimiento(id!!.toLong(),procedimiento,binding)
            }
        }
    }
}