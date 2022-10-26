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
        val idEspecialidad = intent.getLongExtra("idespecialidad", 0)
        val nombreEspecialidad = intent.getStringExtra("nombreespecialidad")
        val idProcedimiento = intent.getLongExtra("idprocedimiento", 0)
        val nombreProcedimiento = intent.getStringExtra("nombreprocedimiento")
        Constant.api.getAllMedicoPaciente(nombreEspecialidad!!,nombreProcedimiento!!,this,binding)
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }

    }


}