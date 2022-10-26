package com.example.gsi


import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityDashboardPacienteBinding
import com.example.gsi.databinding.ActivityEspecialidadesPacienteBinding


class EspecialidadesPacienteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEspecialidadesPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEspecialidadesPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Constant.api.getEspecilidadesPaciente(this@EspecialidadesPacienteActivity, binding)


        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
    }


}