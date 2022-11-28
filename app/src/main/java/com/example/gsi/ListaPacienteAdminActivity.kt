package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isEmpty
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityListaPacienteAdminBinding

class ListaPacienteAdminActivity : AppCompatActivity() {
    private lateinit var binding:ActivityListaPacienteAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListaPacienteAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Constant.api.getAllPacientes(binding)

        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
    }
}