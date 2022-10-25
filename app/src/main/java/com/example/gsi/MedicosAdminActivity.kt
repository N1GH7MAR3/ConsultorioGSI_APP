package com.example.gsi

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
        Constant.api.getAllMedico(this@MedicosAdminActivity,binding)
    }
}