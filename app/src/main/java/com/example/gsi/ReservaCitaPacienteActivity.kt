package com.example.gsi

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.load.engine.Resource
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityReservaCitaPacienteBinding
import java.util.*


class ReservaCitaPacienteActivity : AppCompatActivity(){
    private lateinit var binding: ActivityReservaCitaPacienteBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReservaCitaPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Constant.api.getEspecilidadesPaciente(binding)

        binding.btnReservarCita.setOnClickListener {
        }
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
        binding.btnDate.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, year, monthOfYear, dayOfMonth -> binding.inDate.setText(dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year) },
                year,
                month,
                day
            )
            datePickerDialog.datePicker.minDate = System.currentTimeMillis()+86400000L
            datePickerDialog.show()

        }

    }




}