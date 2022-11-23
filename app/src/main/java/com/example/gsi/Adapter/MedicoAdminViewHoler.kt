package com.example.gsi.Adapter

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Especialidad
import com.example.gsi.Entity.Medico
import com.example.gsi.EspecialidadEditarActivity
import com.example.gsi.MedicoAgregarActivity
import com.example.gsi.MedicoDetalleActivity
import com.example.gsi.R
import com.example.gsi.databinding.ItemEspecialidadesAdminBinding
import com.example.gsi.databinding.ItemMedicoAdminBinding

class MedicoAdminViewHoler (view: View):RecyclerView.ViewHolder(view){
    private val binding = ItemMedicoAdminBinding.bind(view)
    fun render(medicos: Medico){
        binding.txtNombreCompleto.text=medicos.nombre + " "+medicos.apellido_paterno+" "+ medicos.apellido_materno
        binding.txtEspecialidad.text=medicos.especialidad.nombre
        binding.txtdni.text=medicos.dni


        binding.btnDetalle.setOnClickListener {
            val intent = Intent(binding.btnDetalle.context, MedicoDetalleActivity::class.java)
            intent.putExtra("nombre",medicos.nombre)
            intent.putExtra("apePaterno",medicos.apellido_paterno)
            intent.putExtra("apeMaterno",medicos.apellido_materno)
            intent.putExtra("dni",medicos.dni)
            intent.putExtra("pais",medicos.pais?.nombre)
            intent.putExtra("sexo",medicos.sexo.nombre)
            intent.putExtra("estadocivil",medicos.estadoCivil.nombre)
            intent.putExtra("turno",medicos.turno.turno)
            intent.putExtra("horarioingreso",medicos.horario.horaingreso)
            intent.putExtra("horariosalida",medicos.horario.horasalida)
            intent.putExtra("especialidad",medicos.especialidad.nombre)
            binding.btnDetalle.context.startActivity(intent)

        }
        binding.btnEditar.setOnClickListener {
            val intent = Intent(binding.btnEditar.context, MedicoAgregarActivity::class.java)
            intent.putExtra("id",medicos.id.toString())
            intent.putExtra("nombre",medicos.nombre)
            intent.putExtra("apePaterno",medicos.apellido_paterno)
            intent.putExtra("apeMaterno",medicos.apellido_materno)
            intent.putExtra("dni",medicos.dni)
            intent.putExtra("especialidadid",medicos.especialidad.id)
            /*intent.putExtra("pais", medicos.pais.nombre)
            intent.putExtra("sexoid",medicos.sexo.nombre)
            intent.putExtra("estadocivilid",medicos.estadoCivil.nombre)
            intent.putExtra("turnoid",medicos.turno.turno)
            intent.putExtra("horarioingreso",medicos.horario.horaingreso)
            intent.putExtra("horariosalida",medicos.horario.horasalida)*/
            binding.btnEditar.context.startActivity(intent)


        }


        if (medicos.sexo.nombre=="Femenino"){
            binding.ivEspecialidad.setImageResource(R.drawable.img_medicof)
        }else{
            binding.ivEspecialidad.setImageResource(R.drawable.img_medicom)
        }
    }
}