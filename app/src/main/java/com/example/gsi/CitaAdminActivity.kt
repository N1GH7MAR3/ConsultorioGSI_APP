package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityCitaAdminBinding

class CitaAdminActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCitaAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityCitaAdminBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Constant.api.getAllCita(this@CitaAdminActivity,binding)
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
    }
}