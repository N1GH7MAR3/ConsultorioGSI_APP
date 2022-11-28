package com.example.gsi

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.load.engine.Resource
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.createCita
import com.example.gsi.databinding.ActivityReservaCitaPacienteBinding
import java.util.*


class ReservaCitaPacienteActivity : AppCompatActivity(){
    private lateinit var binding: ActivityReservaCitaPacienteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservaCitaPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val idpaciente=intent.getStringExtra("id")
        val id=idpaciente!!.toLong()
        Constant.api.getEspecilidadesPaciente(binding, id.toLong())


        binding.btnRegresar.setOnClickListener {
            finish()
        }
        binding.btnDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth ->

                    binding.btnDisponibilidad.isEnabled=true
                    binding.inDate.setText("$year-${monthOfYear + 1}-$dayOfMonth")
                    if(day+1!=dayOfMonth && binding.inDate.text.toString()!="null"){
                        Constant.api.getEspecilidadesPaciente(binding, id.toLong())
                        binding.btnDisponibilidad.isEnabled=false
                    }
                    },
                year,
                month,
                day
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()+86400000L
            datePickerDialog.show()

        }

    }




}