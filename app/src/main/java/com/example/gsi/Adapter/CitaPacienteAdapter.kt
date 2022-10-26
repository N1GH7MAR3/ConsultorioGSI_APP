package com.example.gsi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Cita
import com.example.gsi.R

class CitaPacienteAdapter(private val citas: List<Cita>) :
    RecyclerView.Adapter<CitaPacienteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaPacienteViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return CitaPacienteViewHolder(layoutInflater.inflate(R.layout.item_cita_paciente,parent,false))
    }

    override fun onBindViewHolder(holder: CitaPacienteViewHolder, position: Int) {
        val item=citas[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = citas.size
}

