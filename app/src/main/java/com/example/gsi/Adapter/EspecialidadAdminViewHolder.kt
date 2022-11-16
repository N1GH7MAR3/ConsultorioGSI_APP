package com.example.gsi.Adapter

import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.Especialidad
import com.example.gsi.EspecialidadEditarActivity
import com.example.gsi.databinding.ItemEspecialidadesAdminBinding

 class EspecialidadAdminViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemEspecialidadesAdminBinding.bind(view)

    fun render(especialidades: Especialidad){
        binding.tvNombreEspecialidad.text=especialidades.nombre
        Glide.with(binding.ivEspecialidad.context).load(especialidades.image).into(binding.ivEspecialidad)
        binding.btnEditar.setOnClickListener {
            val intent = Intent(binding.btnEditar.context, EspecialidadEditarActivity::class.java)
            intent.putExtra("id",especialidades.id.toString())
            intent.putExtra("nombre",especialidades.nombre)
            intent.putExtra("image",especialidades.image)
            binding.btnEditar.context.startActivity(intent)
            (binding.btnEditar.context as Activity).finish()
        }
        binding.btnEliminar.setOnClickListener {
            Constant.api.deleteEspecialidad(especialidades.id,
                binding,especialidades.nombre
            )
        }
    }
}