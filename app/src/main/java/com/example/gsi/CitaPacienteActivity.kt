package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gsi.Adapter.CitaPacienteAdapter
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.Cita
import com.example.gsi.databinding.ActivityCitaPacienteBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CitaPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCitaPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityCitaPacienteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val dni=intent.getStringExtra("dni")
        Constant.api.getCitasPaciente(dni!!.toInt(),this@CitaPacienteActivity,binding )

        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }

    }
}

