package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityEditarPerfilPacienteBinding
import com.example.gsi.databinding.ActivityPacientePerfilBinding

class EditarPerfilPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditarPerfilPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dni = intent.getStringExtra("dni")
        val nombre = intent.getStringExtra("nombre")
        val apePaterno=intent.getStringExtra("apePaterno")
        val apeMaterno=intent.getStringExtra("apeMaterno")
        val telefono=intent.getStringExtra("telefono")
        val direccion=intent.getStringExtra("direccion")
        val correo=intent.getStringExtra("correo")
        val password=intent.getStringExtra("password")


        binding.ediTextApePaterno.setText(apePaterno)
        binding.ediTextApeMaterno.setText(apeMaterno)
        binding.editTextDni.setText(dni)
        binding.editTextDireccion.setText(direccion)
        binding.editTextTelefono.setText(telefono)
        binding.editTexEmail.setText(correo)
        //binding.editTextPassword.setText(password)
        //binding.editTextTexConfirPass.setText(password)

        binding.customPrinciapl.btnRegresar.setOnClickListener {
            finish()
        }
        binding.btnCancelar.setOnClickListener {
            finish()
        }

    }
}