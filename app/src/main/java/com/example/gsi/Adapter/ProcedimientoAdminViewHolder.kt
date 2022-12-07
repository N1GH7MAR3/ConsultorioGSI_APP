package com.example.gsi.Adapter

import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.Procedimiento
import com.example.gsi.ProcedimientoAgregarActivity
import com.example.gsi.databinding.ItemProcedimientoAdminBinding


class ProcedimientoAdminViewHolder (view: View): RecyclerView.ViewHolder(view){
    private val binding= ItemProcedimientoAdminBinding.bind(view)

    fun render(procedimiento: Procedimiento){
        binding.tvNombreProcedimiento.text=procedimiento.nombre
        binding.tvNombreEspecialidad.text=procedimiento.especialidad.nombre
        Glide.with(binding.ivEspecialidad.context).load(procedimiento.especialidad.image).into(binding.ivEspecialidad)

        binding.btnEditar.setOnClickListener {
            val intent = Intent(binding.btnEditar.context, ProcedimientoAgregarActivity::class.java)
            intent.putExtra("id",procedimiento.id.toString())
            intent.putExtra("nombre",procedimiento.nombre)
            intent.putExtra("especialidadid",procedimiento.especialidad.id.toString())
            binding.btnEditar.context.startActivity(intent)

        }
        binding.btnEliminars.setOnClickListener {
            Constant.api.deleteProcedimiento(binding,procedimiento.id)
        }
    }


}