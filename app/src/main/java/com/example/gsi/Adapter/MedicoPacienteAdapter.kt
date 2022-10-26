package com.example.gsi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Medico
import com.example.gsi.Entity.Procedimiento
import com.example.gsi.R

class MedicoPacienteAdapter (private val medicos:List<Medico>,private val procedimiento: String):RecyclerView.Adapter<MedicoPacienteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicoPacienteViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return MedicoPacienteViewHolder(layoutInflater.inflate(R.layout.item_medico_paciente,parent,false))
    }

    override fun onBindViewHolder(holder: MedicoPacienteViewHolder, position: Int) {
        val item=medicos[position]
        holder.render(item,procedimiento)
    }

    override fun getItemCount(): Int =medicos.size


}