package com.example.gsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import com.example.gsi.databinding.ActivityDashboardInvitadoBinding

class DashboardInvitadoActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDashboardInvitadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityDashboardInvitadoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.cardCerrarSesion.setOnClickListener {
            val intent= Intent(this,LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.cardUbi.setOnClickListener {
            val intent= Intent(this,UbicacionActivity::class.java)
            startActivity(intent)

        }

        binding.linearEspeacilidades.setOnClickListener {
            val intent= Intent(this,EspecialidadesPacienteActivity::class.java)
            startActivity(intent)

        }
        binding.linearAcercaDe.setOnClickListener {
            val intent=Intent(this,AcercaNosotrosActivity::class.java)
            startActivity(intent)
        }
    }
}