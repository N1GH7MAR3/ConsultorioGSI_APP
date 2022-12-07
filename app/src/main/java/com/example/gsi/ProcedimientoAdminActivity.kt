package com.example.gsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityProcedimientoAdminBinding

class ProcedimientoAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProcedimientoAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding= ActivityProcedimientoAdminBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val esp=intent.getStringExtra("esp")
        Constant.api.getAllProcedimiento(this@ProcedimientoAdminActivity,binding, esp.toString())
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
        binding.btnRegistrar.setOnClickListener {
            val intent= Intent(this@ProcedimientoAdminActivity,ProcedimientoAgregarActivity::class.java)
            startActivity(intent)
        }
    }
}