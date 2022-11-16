package com.example.gsi

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.createAlergia
import com.example.gsi.Entity.createContactoEmergencia
import com.example.gsi.databinding.ActivityContactoEmergenciaPacienteBinding

class ContactoEmergenciaPacienteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityContactoEmergenciaPacienteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityContactoEmergenciaPacienteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val usuario=intent.getStringExtra("usuario")
        val contactoemergenciaid=intent.getStringExtra("contactoemergenciaid")
        Constant.api.searchPaciente(binding,usuario!!)
        var contactoemergencia:String


        binding.btnConfirmar.isVisible = false
        binding.txtContactoEmergencia.isEnabled = false
        binding.btnAgregar.setOnClickListener {
            binding.txtContactoEmergencia.isEnabled = true
            binding.txtContactoEmergencia.requestFocus()
            contactoemergencia=binding.txtContactoEmergencia.text.toString()

            binding.txtContactoEmergencia.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    binding.btnConfirmar.isVisible = false
                }
                override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    if(s.isNullOrEmpty()){
                        binding.btnConfirmar.isVisible=false
                    }else if(s.toString() != contactoemergencia){
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
            Constant.api.updateContactoEmergencia(
                contactoemergenciaid.toString().toLong(),
                createContactoEmergencia(binding.txtContactoEmergencia.text.toString()), binding, usuario
            )
            binding.btnConfirmar.isVisible = false

        }
        binding.btnEliminar.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Eliminar Contactos De Emergencia")
            dialog.setMessage("Al aceptar eliminar, se borrara todos sus Contactos de Emergencia!")
            dialog.setCancelable(false)
            dialog.setPositiveButton("Confirmar",
                DialogInterface.OnClickListener { dialog, id ->
                    binding.txtContactoEmergencia.setText("")
                    Constant.api.updateContactoEmergencia(
                        contactoemergenciaid.toString().toLong(),
                        createContactoEmergencia(binding.txtContactoEmergencia.text.toString()), binding, usuario
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