package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.gsi.R
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
        val especialidad=intent.getStringExtra("especialidad")



        binding.txtNombreCompleto.text = "Dr. "+nombre+" "+apePaterno+" "+ apeMaterno
        binding.txtEspecialidad.text=especialidad
        binding.txtNombre.text = nombre
        binding.txtApellidiPaterno.text = apePaterno
        binding.txtApellidoMaterno.text = apeMaterno
        binding.txtdni.text = dni
        binding.txtPais.text = pais
        binding.txtEstadoCivil.text = estadocivil
        binding.txtTurno.text = turno
        binding.txtHorario.text = horarioingreso
        binding.txtHorarioSalida.text = horariosalida
        binding.txtSexo.text = sexo
        if (sexo.equals("Femenino")){
            binding.imgMedico.setImageResource(R.drawable.img_medicof)
        }else{
            binding.imgMedico.setImageResource(R.drawable.img_medicom)
        }


        binding.btnRegresar.setOnClickListener {
            finish()
        }
    }
}