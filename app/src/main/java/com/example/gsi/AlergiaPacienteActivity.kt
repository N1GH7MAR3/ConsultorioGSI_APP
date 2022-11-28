package com.example.gsi

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.Alergia
import com.example.gsi.Entity.createAlergia
import com.example.gsi.Entity.createMedicina
import com.example.gsi.databinding.ActivityAlergiaPacienteBinding

class AlergiaPacienteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlergiaPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlergiaPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val usuario = intent.getStringExtra("usuario")
        val alergiaid = intent.getStringExtra("alergiaid")
        Constant.api.searchPaciente(binding, usuario!!)
        var alergia: String
        binding.btnConfirmar.isVisible = false
        binding.txtAlergia.isEnabled = false
        binding.btnAgregar.setOnClickListener {
            binding.txtAlergia.isEnabled = true
            binding.txtAlergia.requestFocus()
            alergia=binding.txtAlergia.text.toString()

            binding.txtAlergia.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    binding.btnConfirmar.isVisible = false
                }
                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(s.isNullOrEmpty()){
                        binding.btnConfirmar.isVisible=false
                    }else if(s.toString() != alergia){
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
            Constant.api.updateAlergia(
                alergiaid.toString().toLong(),
                createAlergia(binding.txtAlergia.text.toString()), binding, usuario
            )
            binding.btnConfirmar.isVisible = false

        }
        binding.btnEliminar.setOnClickListener {
            val dialog:AlertDialog.Builder = AlertDialog.Builder(this)
            val view: View =layoutInflater.inflate(R.layout.layout_error_dailog,null)
            dialog.setView(view)
            val tittle=view.findViewById<TextView>(R.id.textTitle)
            tittle.text="Eliminar Alergias"
            val message=view.findViewById<TextView>(R.id.textMessage)
            message.text="Al aceptar eliminar, se borrara todas sus Alergias!"
            dialog.setCancelable(false)
            dialog.setPositiveButton("Aceptar",
                DialogInterface.OnClickListener { dialog, id ->
                    binding.txtAlergia.setText("")
                    Constant.api.updateAlergia(
                        alergiaid.toString().toLong(),
                        createAlergia(binding.txtAlergia.text.toString()), binding, usuario
                    )
                })
            dialog.setNegativeButton("Cancelar",
                DialogInterface.OnClickListener { dialog, id -> dialog.cancel() })
            dialog.show()
        }
        binding.btnRegresar.setOnClickListener {
            val intent = Intent(this, ControlSaludActivity::class.java)
            intent.putExtra("usuario", usuario)
            finish()
        }

    }


}