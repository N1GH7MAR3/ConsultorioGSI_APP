package com.example.gsi.Adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gsi.Entity.Procedimiento
import com.example.gsi.MedicoPacienteActivity
import com.example.gsi.ProcedimientoPacienteActivity
import com.example.gsi.databinding.ItemProcedimientoPacienteBinding

class ProcedimientoPacienteViewHolder (view:View):RecyclerView.ViewHolder(view){
    private val binding=ItemProcedimientoPacienteBinding.bind(view)

    fun render(procedimiento: Procedimiento){
        binding.tvNombreProcedimiento.text=procedimiento.nombre
        binding.tvNombreEspecialidad.text=procedimiento.especialidad.nombre
        Glide.with(binding.ivEspecialidad.context).load(procedimiento.especialidad.image).into(binding.ivEspecialidad)

    }
}