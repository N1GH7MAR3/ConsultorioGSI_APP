package com.example.gsi

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.createContactoEmergencia
import com.example.gsi.Entity.createContactoMedico
import com.example.gsi.databinding.ActivityContactoMedicoPacienteBinding

class ContactoMedicoPacienteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityContactoMedicoPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityContactoMedicoPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val usuario=intent.getStringExtra("usuario")
        val contactomedicoid=intent.getStringExtra("contactomedicoid")
        Constant.api.searchPaciente(binding,usuario!!)
        var contactomedico:String


        binding.btnConfirmar.isVisible = false
        binding.txtContactoMedico.isEnabled = false
        binding.btnAgregar.setOnClickListener {
            binding.txtContactoMedico.isEnabled = true
            binding.txtContactoMedico.requestFocus()
            contactomedico=binding.txtContactoMedico.text.toString()

            binding.txtContactoMedico.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    binding.btnConfirmar.isVisible = false
                }
                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(s.isNullOrEmpty()){
                        binding.btnConfirmar.isVisible=false
                    }else if(s.toString() != contactomedico){
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
            Constant.api.updateContactoMedico(
                contactomedicoid.toString().toLong(),
                createContactoMedico(binding.txtContactoMedico.text.toString()), binding, usuario
            )
            binding.btnConfirmar.isVisible = false

        }
        binding.btnEliminar.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val view: View =layoutInflater.inflate(R.layout.layout_error_dailog,null)
            dialog.setView(view)
            val tittle=view.findViewById<TextView>(R.id.textTitle)
            tittle.text="Eliminar Contacto Medico"
            val message=view.findViewById<TextView>(R.id.textMessage)
            message.text="Al aceptar eliminar, se borrara todos sus Contactos Medicos!"
            dialog.setCancelable(false)
            dialog.setPositiveButton("Confirmar",
                DialogInterface.OnClickListener { dialog, id ->
                    binding.txtContactoMedico.setText("")
                    Constant.api.updateContactoMedico(
                        contactomedicoid.toString().toLong(),
                        createContactoMedico(binding.txtContactoMedico.text.toString()), binding, usuario
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