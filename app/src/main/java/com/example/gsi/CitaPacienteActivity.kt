package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityCitaPacienteBinding

class CitaPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCitaPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityCitaPacienteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val dni=intent.getStringExtra("dni")
        Constant.api.getCitasPaciente(dni!!.toInt(),this@CitaPacienteActivity,binding )

    }
}