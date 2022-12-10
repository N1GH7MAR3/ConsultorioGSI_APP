package com.example.gsi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Cita
import com.example.gsi.R

class CitaAdminAdapter (private val citas: List<Cita>) :
    RecyclerView.Adapter<CitaAdminViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CitaAdminViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return CitaAdminViewHolder(layoutInflater.inflate(R.layout.item_cita_admin, parent,false))
    }

    override fun onBindViewHolder(holder: CitaAdminViewHolder, position: Int) {
        val item=citas[position]
        holder.render(item)
    }
    override fun getItemCount(): Int = citas.size
}