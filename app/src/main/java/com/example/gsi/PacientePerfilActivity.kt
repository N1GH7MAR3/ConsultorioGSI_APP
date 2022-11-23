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
        val id=intent.getStringExtra("id")
        val nombre=intent.getStringExtra("nombre")
        val apePaterno=intent.getStringExtra("apePaterno")
        val apeMaterno=intent.getStringExtra("apeMaterno")
        val dni=intent.getStringExtra("dni")
        val direccion=intent.getStringExtra("direccion")
        val telefono=intent.getStringExtra("telefono")
        val correo=intent.getStringExtra("correo")
        val paisid=intent.getStringExtra("paisid")
        val paisnombre=intent.getStringExtra("paisnombre")
        val estadocivilid=intent.getStringExtra("estadocivilid")
        val estadocivilnombre=intent.getStringExtra("estadocivilnombre")
        val sexoid=intent.getStringExtra("sexoid")
        val sexonombre=intent.getStringExtra("sexonombre")
        val usuarioid=intent.getStringExtra("usuarioid")
        val usuario=intent.getStringExtra("usuario")
        val password=intent.getStringExtra("password")


        binding.txtNombreMain.text=nombre
        binding.txtNombre.text= nombre
        binding.txtCorreoMain.text=correo
        binding.txtApellidiPaterno.text=apePaterno
        binding.txtApellidoMaterno.text=apeMaterno
        binding.txtTelefono.text=telefono
        binding.txtPais.text=paisnombre

        binding.llRegresar.setOnClickListener {
            val intent=Intent(this,DashboardPacienteActivity::class.java)
            intent.putExtra("usuario",dni.toString())
            startActivity(intent)
            finish()
        }
        binding.btnEditar.setOnClickListener {
            val intent= Intent(this,RegisterActivity::class.java)
            intent.putExtra("id",id)
            intent.putExtra("nombre",nombre)
            intent.putExtra("apePaterno",apePaterno)
            intent.putExtra("apeMaterno",apeMaterno)
            intent.putExtra("dni", dni)
            intent.putExtra("direccion",direccion)
            intent.putExtra("telefono",telefono)
            intent.putExtra("correo",correo)
            intent.putExtra("paisid",paisid)
            intent.putExtra("paisnombre",paisnombre)
            intent.putExtra("estadocivilid",estadocivilid)
            intent.putExtra("estadocivilnombre",estadocivilnombre)
            intent.putExtra("sexoid",sexoid)
            intent.putExtra("sexonombre",sexonombre)
            intent.putExtra("usuarioid",usuarioid)
            intent.putExtra("usuario",usuario)
            intent.putExtra("password",password)
            startActivity(intent)
        }
    }
}