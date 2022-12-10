package com.example.gsi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.gsi.databinding.ActivityDashboardAdminBinding

class DashboardAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardAdminBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDashboardAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nombre = intent.getStringExtra("nombre")
        binding.txtNombre.text = nombre
        binding.cardEspecialidad.setOnClickListener {
            val intent = Intent(this@DashboardAdminActivity, EspecialidadesAdminActivity::class.java)
            startActivity(intent)
        }
        binding.carMedicos.setOnClickListener {
            val intent=Intent(this@DashboardAdminActivity,MedicosAdminActivity::class.java)
            startActivity(intent)
        }
        binding.cardCerrarSesion.setOnClickListener {
            val intent=Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.cardPaciente.setOnClickListener {
            val intent=Intent(this@DashboardAdminActivity,ListaPacienteAdminActivity::class.java)

            startActivity(intent)
        }
        binding.cardProcedimientos.setOnClickListener {
            val intent=Intent(this,ProcedimientoAdminActivity::class.java)
            startActivity(intent)
        }
        binding.cardCitas.setOnClickListener {
            val intent=Intent(this,CitaAdminActivity::class.java)
            startActivity(intent)
        }
    }

    
}