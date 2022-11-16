package com.example.gsi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Paciente
import com.example.gsi.R

class PacienteAdapter(private val pacientes:List<Paciente>):RecyclerView.Adapter<PacienteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacienteViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return PacienteViewHolder(layoutInflater.inflate(R.layout.item_lista_paciente,parent,false))
    }

    override fun onBindViewHolder(holder: PacienteViewHolder, position: Int) {
        val item=pacientes[position]
        holder.render(item)
    }

    override fun getItemCount(): Int =pacientes.size
}