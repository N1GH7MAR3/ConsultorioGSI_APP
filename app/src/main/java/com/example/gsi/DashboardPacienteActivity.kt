package com.example.gsi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gsi.Entity.Cita
import com.example.gsi.databinding.ActivityDashboardPacienteBinding

class DashboardPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val dni = intent.getStringExtra("dni")
        val nombre = intent.getStringExtra("nombre")
        val enfermedad=intent.getStringExtra("enfermedad")
        val medicina=intent.getStringExtra("medicina")

        //perfil
        val apePaterno=intent.getStringExtra("apePaterno")
        val apeMaterno=intent.getStringExtra("apeMaterno")
        val telefono=intent.getStringExtra("telefono")
        val direccion=intent.getStringExtra("direccion")
        val correo=intent.getStringExtra("correo")
        val password=intent.getStringExtra("password")
        val sexoid=intent.getStringExtra("sexoid")

        binding.txtNombre.text = "Hola, ${nombre}";
        binding.cardEspecialidades.setOnClickListener {
            val intent =
                Intent(this@DashboardPacienteActivity, EspecialidadesPacienteActivity::class.java)
            startActivity(intent)
        }
        binding.cardCitas.setOnClickListener {
            val intent = Intent(this@DashboardPacienteActivity, CitaPacienteActivity::class.java)
            intent.putExtra("dni", dni)
            startActivity(intent)
        }
        binding.cardCerrarSesion.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.cardControlSalud.setOnClickListener {
            val intent=Intent(this,ControlSaludActivity::class.java)
            intent.putExtra("enfermedad",enfermedad)
            intent.putExtra("medicina",medicina)
            startActivity(intent)

        }
        binding.imagePerfil.setOnClickListener {
            val intent=Intent(this,PacientePerfilActivity::class.java)
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

        binding.cardAcercaNosotros.setOnClickListener {
            val intent=Intent(this,AcercaNosotrosActivity::class.java)


            startActivity(intent)

        }



    }
}