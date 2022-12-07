package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.*
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
        val horarioingreso=intent.getStringExtra("horarioingreso")
        val horariosalida=intent.getStringExtra("horariosalida")
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
            binding.txtInputHorario.setText("$horarioingreso - $horariosalida")
            binding.spEspecialidad.isEnabled=false


        }else{
            binding.textView3.text = "Agregar Medico"
            binding.btnGuardar.text = "Agregar Medico"
            binding.txtLayautId.isVisible=false
            binding.txtInputId.isVisible=false
        }
        Constant.api.getAllEspecialidades(binding, especialidadid.toString())
        Constant.api.getAllPais(binding, paisid.toString())
        Constant.api.getAllEstadoCivil(binding, estadocivilid.toString())
        Constant.api.getAllTurno(binding,turnoid.toString())
        Constant.api.getAllSexo(binding, sexoid.toString())
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
        binding.btnGuardar.setOnClickListener {
            val especialidad=putEspecialidad(binding.spEspecialidad.selectedItemId)
            val pais=putPais(binding.spPais.selectedItemId)
            val sexo=putSexo(binding.spSexo.selectedItemId)
            val turno=putTurno(binding.spTurno.selectedItemId)
            val estadoCivil=putEstadoCivil(binding.spEstadoCivil.selectedItemId)
            val medico=createMedico(binding.txtInputNombre.text.toString(),binding.txtInputPaterno.text.toString(),binding.txtInputMaterno.text.toString(),binding.txtInputDni.text.toString(),pais,estadoCivil,sexo,turno, especialidad)
            if(id.toString() == "null"){
                Constant.api.createMedico(medico,binding)
            }else{
                Constant.api.updateMedico(id!!.toLong(),medico,binding)
            }

        }
    }
}