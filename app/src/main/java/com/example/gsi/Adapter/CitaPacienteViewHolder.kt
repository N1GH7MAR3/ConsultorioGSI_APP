package com.example.gsi.Adapter

import android.annotation.SuppressLint
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Cita
import com.example.gsi.databinding.ItemCitaPacienteBinding
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*

class CitaPacienteViewHolder (view:View):RecyclerView.ViewHolder(view){
    private val binding=ItemCitaPacienteBinding.bind(view)



    fun render(citas:Cita){
        binding.tvNombre.text=citas.paciente.nombre
        binding.tvEspecialidad.text=citas.especialidad.nombre
        val fecha=SimpleDateFormat("dd-MM-yy")
        binding.tvFechacita.text= citas.fechacita
        binding.tvMedico.text=citas.medico.nombre
        binding.tvFecharegistro.text=fecha.format(citas.fecharegistro)
        binding.tvProcedimiento.text=citas.procedimiento.nombre
    }
}