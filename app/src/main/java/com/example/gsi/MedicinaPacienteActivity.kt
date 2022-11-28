package com.example.gsi

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.createEnfermedad
import com.example.gsi.Entity.createMedicina

import com.example.gsi.databinding.ActivityMedicinaPacienteBinding


class MedicinaPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMedicinaPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMedicinaPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val usuario=intent.getStringExtra("usuario")
        val medicinaid=intent.getStringExtra("medicinaid")
        Constant.api.searchPaciente(binding,usuario!!)
        var medicina:String


        binding.btnConfirmar.isVisible = false
        binding.txtMedicina.isEnabled = false
        binding.btnAgregar.setOnClickListener {
            binding.txtMedicina.isEnabled = true
            binding.txtMedicina.requestFocus()
            medicina=binding.txtMedicina.text.toString()

            binding.txtMedicina.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    binding.btnConfirmar.isVisible = false
                }
                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(s.isNullOrEmpty()){
                        binding.btnConfirmar.isVisible=false
                    }else if(s.toString() != medicina){
                        binding.btnConfirmar.isVisible=true
                    }
                }
                override fun afterTextChanged(s: Editable?) {
                    if (s.toString().isEmpty()) {
                        binding.btnConfirmar.isVisible = false
                    }
                }

            })}

        binding.btnConfirmar.setOnClickListener {
            Constant.api.updateMedicina(
                medicinaid.toString().toLong(),
                createMedicina(binding.txtMedicina.text.toString()), binding, usuario
            )
            binding.btnConfirmar.isVisible = false

        }
        binding.btnEliminar.setOnClickListener {
            val dialog:AlertDialog.Builder = AlertDialog.Builder(this)
            val view: View =layoutInflater.inflate(R.layout.layout_error_dailog,null)
            dialog.setView(view)
            val tittle=view.findViewById<TextView>(R.id.textTitle)
            tittle.text="Eliminar Medicina"
            val message=view.findViewById<TextView>(R.id.textMessage)
            message.text="Al aceptar eliminar, se borrara todas sus Medicinas!"
            dialog.setCancelable(false)

            dialog.setPositiveButton("Aceptar",DialogInterface.OnClickListener { dialogInterface, i -> binding.txtMedicina.setText("")
                Constant.api.updateMedicina(
                    medicinaid.toString().toLong(),
                    createMedicina(binding.txtMedicina.text.toString()), binding, usuario
                )  })
            dialog.setNegativeButton("Cancelar",DialogInterface.OnClickListener { dialogInterface, i -> dialogInterface.dismiss() })
            dialog.show()


        }
        binding.btnRegresar.setOnClickListener {
            val intent = Intent(this, ControlSaludActivity::class.java)
            intent.putExtra("usuario", usuario)
            finish()
        }
    }
}