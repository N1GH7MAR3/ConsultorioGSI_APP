package com.example.gsi

import android.R
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.*
import com.example.gsi.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        Constant.api.getAllPais(this,binding)
        Constant.api.getAllEstadoCivil(this,binding)
        Constant.api.getAllSexo(this,binding)

        binding.btnRegistrate.setOnClickListener {

            val usuario=createUsuarioPaciente(binding.editTextDni.text.toString(),binding.editTextDni.text.toString(),
                createRolUsuario(1)
            )
            Constant.api.createUsuarioPacienteControlSalud(binding,usuario)
        }

    }
}