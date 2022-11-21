package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityProcedimientoPacienteBinding

class ProcedimientoPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProcedimientoPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityProcedimientoPacienteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val medicoid=intent.getStringExtra("medicoid")

            Constant.api.getAllProcedimientosxMedico(binding,medicoid!!.toLong())

        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }

    }
}