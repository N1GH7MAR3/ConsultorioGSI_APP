package com.example.gsi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Especialidad
import com.example.gsi.R

class EspecialidadAdminAdapter (private val especialidades:List<Especialidad>) :RecyclerView.Adapter<EspecialidadAdminViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspecialidadAdminViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return EspecialidadAdminViewHolder(layoutInflater.inflate(R.layout.item_especialidades_admin,parent,false))
    }

    override fun onBindViewHolder(holder: EspecialidadAdminViewHolder, position: Int) {
        val item=especialidades[position]
        holder.render(item)
    }

    override fun getItemCount(): Int =especialidades.size


}