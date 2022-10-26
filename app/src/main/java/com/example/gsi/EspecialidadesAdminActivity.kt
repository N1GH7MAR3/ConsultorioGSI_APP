package com.example.gsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityEspecialidadesAdminBinding
import com.example.gsi.databinding.CustomTolbarPrincipalBinding

class EspecialidadesAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEspecialidadesAdminBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityEspecialidadesAdminBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Constant.api.getEspecilidadesAdmin(this@EspecialidadesAdminActivity, binding)
        binding.btnAgregar.setOnClickListener {
            val intent = Intent(this@EspecialidadesAdminActivity, EspecialidadEditarActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
    }
}




