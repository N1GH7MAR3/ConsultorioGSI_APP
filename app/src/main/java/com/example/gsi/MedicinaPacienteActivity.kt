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
            val dialog = AlertDialog.Builder(this)
            dialog.setTitle("Eliminar Medicina")
            dialog.setMessage("Al aceptar eliminar, se borrara todas sus Medicinas!")
            dialog.setCancelable(false)
            dialog.setPositiveButton("Confirmar",
                DialogInterface.OnClickListener { dialog, id ->
                    binding.txtMedicina.setText("")
                    Constant.api.updateMedicina(
                        medicinaid.toString().toLong(),
                        createMedicina(binding.txtMedicina.text.toString()), binding, usuario
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