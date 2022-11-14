package com.example.gsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.databinding.ActivityDashboardPacienteBinding
import com.example.gsi.databinding.ActivityPacientePerfilBinding

class PacientePerfilActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPacientePerfilBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPacientePerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dni = intent.getStringExtra("dni")
        val nombre = intent.getStringExtra("nombre")
        val apePaterno=intent.getStringExtra("apePaterno")
        val apeMaterno=intent.getStringExtra("apeMaterno")
        val telefono=intent.getStringExtra("telefono")
        val direccion=intent.getStringExtra("direccion")
        val correo=intent.getStringExtra("correo")
        val password=intent.getStringExtra("password")

        val sexoid=intent.getStringExtra("sexoid")



        binding.txtNombreMain.text=nombre
        binding.txtNombre.text= nombre

        binding.txtCorreoMain.text=correo
        binding.txtCorreoMain.text=correo
        binding.txtApellidiPaterno.text=apePaterno
        binding.txtApellidoMaterno.text=apeMaterno
        binding.txtTelefono.text=telefono
        binding.txtDireccion.text=direccion
        //binding.txtCorreo.text=correo


        binding.llRegresar.setOnClickListener {
            finish()
        }
        binding.btnEditar.setOnClickListener {
            val intent= Intent(this,EditarPerfilPacienteActivity::class.java)
            intent.putExtra("dni", dni)
            intent.putExtra("nombre",nombre)
            intent.putExtra("apePaterno",apePaterno)
            intent.putExtra("apeMaterno",apeMaterno)
            intent.putExtra("telefono",telefono)
            intent.putExtra("direccion",direccion)
            intent.putExtra("correo",correo)
            intent.putExtra("password",password)
            intent.putExtra("sexoid",sexoid)

            startActivity(intent)

        }
    }
}