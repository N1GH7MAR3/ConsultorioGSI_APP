package com.example.gsi.Adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Medico
import com.example.gsi.Entity.Paciente
import com.example.gsi.databinding.ItemListaPacienteBinding

class PacienteViewHolder (view:View):RecyclerView.ViewHolder(view){
    private val binding=ItemListaPacienteBinding.bind(view)

    fun render(pacientes: Paciente){
        binding.txtNombre.text=pacientes.nombre + " "+pacientes.apellido_paterno +" "+ pacientes.apellido_materno
        binding.txtdni.text=pacientes.dni.toString()
        binding.txtDireccion.text=pacientes.direccion
        binding.txtSexo.text=pacientes.sexo.nombre
        binding.txtTelefono.text=pacientes.telefono

    }
}