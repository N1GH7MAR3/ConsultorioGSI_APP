package com.example.gsi.Adapter

import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Medico
import com.example.gsi.Entity.Procedimiento
import com.example.gsi.databinding.ItemMedicoPacienteBinding

class MedicoPacienteViewHolder (view:View):RecyclerView.ViewHolder (view){
    private val binding=ItemMedicoPacienteBinding.bind(view)

    fun render(medicos: Medico,procedimiento: String){
        binding.txtNombreCompleto.text=medicos.nombre +" "+ medicos.apellido_materno + " "+medicos.apellido_paterno
        binding.txtEspecialidad.text=medicos.especialidad.nombre
        binding.txtProcedimiento.text=procedimiento
        binding.txtdni.text=medicos.dni
    }
}