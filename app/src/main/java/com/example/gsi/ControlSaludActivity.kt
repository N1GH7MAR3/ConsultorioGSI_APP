package com.example.gsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityControlSaludBinding
import com.example.gsi.databinding.ActivityMedicoAgregarBinding

class ControlSaludActivity : AppCompatActivity() {
    private lateinit var binding: ActivityControlSaludBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityControlSaludBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val usuario=intent.getStringExtra("usuario")
        Constant.api.searchPaciente(binding, usuario!!)
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            val intent=Intent(this,DashboardPacienteActivity::class.java)
            intent.putExtra("usuario",usuario)
            finish()
        }
    }
}