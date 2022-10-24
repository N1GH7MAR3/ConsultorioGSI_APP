package com.example.gsi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Especialidad
import com.example.gsi.R

class EspecialidadAdapter(private val especialidades:List<Especialidad>) :RecyclerView.Adapter<EspecialidadViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspecialidadViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return EspecialidadViewHolder(layoutInflater.inflate(R.layout.item_especialidades_paciente,parent,false))
    }

    override fun onBindViewHolder(holder: EspecialidadViewHolder, position: Int) {
        val item=especialidades[position]
        holder.render(item)
        }

    override fun getItemCount(): Int =especialidades.size

}