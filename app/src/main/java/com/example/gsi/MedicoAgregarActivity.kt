package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.databinding.ActivityMedicoAgregarBinding

class MedicoAgregarActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMedicoAgregarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMedicoAgregarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
    }
}