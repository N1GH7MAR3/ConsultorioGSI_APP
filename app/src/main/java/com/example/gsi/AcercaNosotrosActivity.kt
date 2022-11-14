package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.databinding.ActivityAcercaNosotrosBinding
import com.example.gsi.databinding.ActivityEnfermedadPacienteBinding
import com.example.gsi.databinding.ActivityMedicinaPacienteBinding

class AcercaNosotrosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAcercaNosotrosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityAcercaNosotrosBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnRegresar.setOnClickListener {
            finish()
        }
    }
}