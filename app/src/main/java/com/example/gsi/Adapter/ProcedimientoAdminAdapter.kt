package com.example.gsi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Procedimiento
import com.example.gsi.R

class ProcedimientoAdminAdapter (private val procedimiento:List<Procedimiento>): RecyclerView.Adapter<ProcedimientoAdminViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProcedimientoAdminViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return ProcedimientoAdminViewHolder(layoutInflater.inflate(R.layout.item_procedimiento_admin,parent   ,false))
    }

    override fun onBindViewHolder(holder: ProcedimientoAdminViewHolder, position: Int) {
        val item=procedimiento[position]
        holder.render(item)
    }

    override fun getItemCount(): Int =procedimiento.size
}