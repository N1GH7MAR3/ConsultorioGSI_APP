package com.example.gsi.Adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Cita
import com.example.gsi.databinding.ItemCitaAdminBinding
import java.text.SimpleDateFormat

class CitaAdminViewHolder (view: View): RecyclerView.ViewHolder(view){
    private val binding = ItemCitaAdminBinding.bind(view)
    fun render(citas: Cita){
        binding.tvNombre.text=citas.paciente.nombre
        binding.tvDni.text=citas.paciente.dni.toString()
        binding.tvEspecialidad.text=citas.especialidad.nombre
        val fecha= SimpleDateFormat("dd-MM-yy")
        binding.tvFechacita.text= citas.fechacita
        binding.tvMedico.text=citas.medico.nombre
        binding.tvFecharegistro.text=fecha.format(citas.fecharegistro)
        binding.tvProcedimiento.text=citas.procedimiento.nombre
        }

}