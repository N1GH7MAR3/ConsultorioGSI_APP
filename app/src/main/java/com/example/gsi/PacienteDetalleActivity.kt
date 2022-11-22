package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.databinding.ActivityMedicoDetalleBinding
import com.example.gsi.databinding.ActivityPacienteDetalleBinding

class PacienteDetalleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPacienteDetalleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityPacienteDetalleBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val nombre = intent.getStringExtra("nombre")
        val apePaterno = intent.getStringExtra("apePaterno")
        val apeMaterno = intent.getStringExtra("apeMaterno")
        val direccion = intent.getStringExtra("direccion")
        val estadoCivil = intent.getStringExtra("estadoCivil")
        val sexo = intent.getStringExtra("sexo")
        val telefono = intent.getStringExtra("telefono")
        val pais = intent.getStringExtra("pais")
        val correo = intent.getStringExtra("correo")

        binding.txtNombreCompleto.setText(nombre+" "+apePaterno+" "+apeMaterno)
        binding.txtNombre.setText(nombre)
        binding.txtApellidiPaterno.setText(apePaterno)
        binding.txtApellidoMaterno.setText(apeMaterno)
        binding.txtDireccion.setText(direccion)
        binding.txtEstadoCivil.setText(estadoCivil)
        binding.txtSexo.setText(sexo)
        binding.txtTelefono.setText(telefono)

        binding.txtPais.setText(pais)
        binding.txtCorreo.setText(correo)

        binding.btnRegresar.setOnClickListener {
            finish()
        }



    }
}