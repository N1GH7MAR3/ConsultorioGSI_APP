package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityReservaCitaPacienteBinding

class ReservaCitaPacienteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityReservaCitaPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityReservaCitaPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Constant.api.getEspecilidadesPaciente(binding)

        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
    }
}