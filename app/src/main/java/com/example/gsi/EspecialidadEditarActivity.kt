package com.example.gsi

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.gsi.Adapter.EspecialidadAdminAdapter
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.Especialidad
import com.example.gsi.Entity.createEspecialidad
import com.example.gsi.databinding.ActivityEspecialidadEditarBinding

class EspecialidadEditarActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEspecialidadEditarBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityEspecialidadEditarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val id = intent.getStringExtra("id")
        val nombre = intent.getStringExtra("nombre")
        val image = intent.getStringExtra("image")
        binding.txtInputId.setText(id)
        binding.txtInputNombre.setText(nombre)
        binding.txtInputImage.setText(image)
        Glide.with(this@EspecialidadEditarActivity).load(image).into(binding.imgEspecialidad)

        binding.btnVisualizar.setOnClickListener {
            Glide.with(this@EspecialidadEditarActivity).load(binding.txtInputImage.text.toString())
                .into(binding.imgEspecialidad)
        }

        binding.btnGuardar.setOnClickListener {
            if(binding.txtInputId.text.toString()==""){
                val espc = createEspecialidad(
                    binding.txtInputNombre.text.toString(),
                    binding.txtInputImage.text.toString()
                )
                Constant.api.createEspecialidad(
                    espc,
                    binding
                )
            }else{
                val esp=Especialidad(id!!.toLong(),binding.txtInputNombre.text.toString(),
                    binding.txtInputImage.text.toString())
                Constant.api.updateEspecialidad(id.toLong(),esp,binding)
            }
        }
        binding.btnLimpiar.setOnClickListener {
            binding.txtInputNombre.setText("")
            binding.txtInputImage.setText("")
        }
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
    }


}