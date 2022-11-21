package com.example.gsi.Adapter

import android.content.Intent
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.gsi.Entity.Medico
import com.example.gsi.Entity.Procedimiento
import com.example.gsi.MedicoPacienteActivity
import com.example.gsi.ProcedimientoPacienteActivity
import com.example.gsi.R
import com.example.gsi.databinding.ItemMedicoPacienteBinding

class MedicoPacienteViewHolder (view:View):RecyclerView.ViewHolder (view){
    private val binding=ItemMedicoPacienteBinding.bind(view)

    fun render(medicos: Medico){
        binding.txtNombreCompleto.text=medicos.nombre + " "+medicos.apellido_paterno+" "+ medicos.apellido_materno
        binding.txtEspecialidad.text=medicos.especialidad.nombre
        binding.txtdni.text=medicos.dni
        if (medicos.sexo.nombre=="Femenino"){
            binding.ivEspecialidad.setImageResource(R.drawable.img_medicof)
        }else{
            binding.ivEspecialidad.setImageResource(R.drawable.img_medicom)
<<<<<<< HEAD
=======
        }
        itemView.setOnClickListener {
            val intent= Intent(binding.ivEspecialidad.context, ProcedimientoPacienteActivity::class.java)
            intent.putExtra("medicoid",medicos.id.toString())
            binding.ivEspecialidad.context.startActivity(intent)
>>>>>>> 041b185fb15e9241ebbd5f39851b6fc3419e91a4
        }
    }
}