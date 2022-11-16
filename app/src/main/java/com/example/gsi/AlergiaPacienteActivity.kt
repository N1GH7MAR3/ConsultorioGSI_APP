package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.databinding.ActivityAlergiaPacienteBinding

class AlergiaPacienteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAlergiaPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityAlergiaPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val alergia=intent.getStringExtra("alergia")
        binding.txtAlergia.isEnabled=false
        binding.txtAlergia.setText(alergia)

        binding.btnRegresar.setOnClickListener {
            finish()
        }
    }
}