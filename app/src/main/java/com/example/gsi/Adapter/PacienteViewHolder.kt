package com.example.gsi.Adapter

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Medico
import com.example.gsi.Entity.Paciente
import com.example.gsi.MedicoDetalleActivity
import com.example.gsi.PacienteDetalleActivity
import com.example.gsi.R
import com.example.gsi.databinding.ItemListaPacienteBinding

class PacienteViewHolder (view:View):RecyclerView.ViewHolder(view){
    private val binding=ItemListaPacienteBinding.bind(view)

    fun render(pacientes: Paciente){
        binding.txtNombre.text= "${pacientes.nombre} ${pacientes.apellido_paterno} ${pacientes.apellido_materno}"
        binding.txtdni.text=pacientes.dni.toString()
        binding.txtDireccion.text=pacientes.direccion
        binding.txtSexo.text=pacientes.sexo.nombre
        binding.txtTelefono.text=pacientes.telefono
        if (pacientes.sexo.nombre=="Femenino"){
            binding.imgViewPaciente.setImageResource(R.drawable.img_girl2)
        }else{
            binding.imgViewPaciente.setImageResource(R.drawable.img_boy2)
        }
        binding.btnDetallePaciente.setOnClickListener {
            val intent = Intent(binding.btnDetallePaciente.context, PacienteDetalleActivity::class.java)
            intent.putExtra("nombre",pacientes.nombre)
            intent.putExtra("apePaterno",pacientes.apellido_paterno)
            intent.putExtra("apeMaterno",pacientes.apellido_materno)
            intent.putExtra("dni",pacientes.dni)
            intent.putExtra("direccion",pacientes.direccion)
            intent.putExtra("estadoCivil",pacientes.estadoCivil.nombre)
            intent.putExtra("sexo",pacientes.sexo.nombre)
            intent.putExtra("telefono",pacientes.telefono)
            intent.putExtra("pais",pacientes.pais.nombre)
            intent.putExtra("correo",pacientes.correo)

            binding.btnDetallePaciente.context.startActivity(intent)

        }

    }
}