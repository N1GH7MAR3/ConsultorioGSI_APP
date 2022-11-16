package com.example.gsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityMedicosAdminBinding

class MedicosAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicosAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityMedicosAdminBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val esp=intent.getStringExtra("esp")
        Constant.api.getAllMedico(this@MedicosAdminActivity,binding,esp!!)
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
        binding.btnRegistrar.setOnClickListener {
            val intent=Intent(this@MedicosAdminActivity,MedicoAgregarActivity::class.java)
            startActivity(intent)
        }
    }
}