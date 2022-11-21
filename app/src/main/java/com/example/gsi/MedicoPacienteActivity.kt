package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gsi.Adapter.MedicoPacienteAdapter
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.Medico
import com.example.gsi.databinding.ActivityMedicoPacienteBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MedicoPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicoPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMedicoPacienteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val nombreEspecialidad = intent.getStringExtra("nombreespecialidad")
        Constant.api.getAllMedicoPaciente(nombreEspecialidad!!,binding)
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }

    }


}