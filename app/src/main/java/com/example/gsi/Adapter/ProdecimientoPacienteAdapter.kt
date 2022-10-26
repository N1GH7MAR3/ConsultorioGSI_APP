package com.example.gsi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Procedimiento
import com.example.gsi.R

class ProdecimientoPacienteAdapter (private val procedimiento:List<Procedimiento>):RecyclerView.Adapter<ProcedimientoPacienteViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProcedimientoPacienteViewHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        return ProcedimientoPacienteViewHolder(layoutInflater.inflate(R.layout.item_procedimiento_paciente,parent   ,false))
    }

    override fun onBindViewHolder(holder: ProcedimientoPacienteViewHolder, position: Int) {
        val item=procedimiento[position]
        holder.render(item)
    }

    override fun getItemCount(): Int =procedimiento.size


}