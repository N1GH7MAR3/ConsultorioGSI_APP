package com.example.gsi.Adapter

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Especialidad
import com.example.gsi.Entity.Medico
import com.example.gsi.R
import com.example.gsi.databinding.ItemEspecialidadesAdminBinding
import com.example.gsi.databinding.ItemMedicoAdminBinding

class MedicoAdminViewHoler (view: View):RecyclerView.ViewHolder(view){
    private val binding = ItemMedicoAdminBinding.bind(view)
    fun render(medicos: Medico){
        binding.txtNombreCompleto.text=medicos.nombre + " "+medicos.apellido_paterno+" "+ medicos.apellido_materno
        binding.txtEspecialidad.text=medicos.especialidad.nombre
        binding.txtdni.text=medicos.dni
        if (medicos.sexo.nombre=="Femenino"){
            binding.ivEspecialidad.setImageResource(R.drawable.img_medicof)
        }else{
            binding.ivEspecialidad.setImageResource(R.drawable.img_medicom)
        }
    }
}