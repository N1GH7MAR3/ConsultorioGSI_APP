package com.example.gsi.Adapter

import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gsi.Entity.Especialidad
import com.example.gsi.MedicoPacienteActivity
import com.example.gsi.ProcedimientoPacienteActivity

import com.example.gsi.databinding.ItemEspecialidadesPacienteBinding

class EspecialidadViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemEspecialidadesPacienteBinding.bind(view)
    fun render(especialidades: Especialidad) {
        binding.tvNombreEspecialidad.text = especialidades.nombre
        val id = especialidades.id
        Glide.with(binding.ivEspecialidad.context).load(especialidades.image)
            .into(binding.ivEspecialidad)

        itemView.setOnClickListener {
            val intent=Intent(binding.ivEspecialidad.context,MedicoPacienteActivity::class.java)
            intent.putExtra("nombreespecialidad",especialidades.nombre)
            binding.ivEspecialidad.context.startActivity(intent)
        }
    }
}