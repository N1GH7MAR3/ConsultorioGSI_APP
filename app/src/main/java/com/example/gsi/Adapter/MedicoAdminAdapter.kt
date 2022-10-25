package com.example.gsi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Medico
import com.example.gsi.R

class MedicoAdminAdapter(private val medicos:List<Medico>) : RecyclerView.Adapter<MedicoAdminViewHoler>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicoAdminViewHoler {
        val layoutInflater= LayoutInflater.from(parent.context)
        return MedicoAdminViewHoler(layoutInflater.inflate(R.layout.item_medico_admin,parent,false))
    }

    override fun onBindViewHolder(holder: MedicoAdminViewHoler, position: Int) {
        val item=medicos[position]
        holder.render(item)
    }

    override fun getItemCount(): Int =medicos.size
}