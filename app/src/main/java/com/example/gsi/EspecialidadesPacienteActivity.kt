package com.example.gsi


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


import com.example.gsi.Constans.Constant



import com.example.gsi.databinding.ActivityEspecialidadesPacienteBinding


class EspecialidadesPacienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEspecialidadesPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEspecialidadesPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Constant.api.getEspecilidadesPaciente(this@EspecialidadesPacienteActivity, binding)
    }


}