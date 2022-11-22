package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.databinding.ActivityEspecialidadesAdminBinding
import com.example.gsi.databinding.ActivityMedicoDetalleBinding

class MedicoDetalleActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicoDetalleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding=ActivityMedicoDetalleBinding.inflate(layoutInflater)

        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        val nombre = intent.getStringExtra("nombre")
        val apePaterno = intent.getStringExtra("apePaterno")
        val apeMaterno = intent.getStringExtra("apeMaterno")
        val dni = intent.getStringExtra("dni")
        val pais = intent.getStringExtra("pais")
        val estadocivil = intent.getStringExtra("estadocivil")
        val turno = intent.getStringExtra("turno")
        val horarioingreso = intent.getStringExtra("horarioingreso")
        val horariosalida = intent.getStringExtra("horariosalida")
        val sexo = intent.getStringExtra("sexo")



        binding.txtNombreCompleto.setText("Dr. "+nombre+" "+apePaterno+" "+ apeMaterno)
        binding.txtNombre.setText(nombre)
        binding.txtApellidiPaterno.setText(apePaterno)
        binding.txtApellidoMaterno.setText(apeMaterno)
        binding.txtdni.setText(dni)
        binding.txtPais.setText(pais)
        binding.txtEstadoCivil.setText(estadocivil)
        binding.txtTurno.setText(turno)
        binding.txtHorario.setText(horarioingreso)
        binding.txtHorarioSalida.setText(horariosalida)

        binding.txtSexo.setText(sexo)



        binding.btnRegresar.setOnClickListener {
            finish()
        }
    }
}