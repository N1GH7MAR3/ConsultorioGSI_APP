package com.example.gsi

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.createRolUsuario
import com.example.gsi.Entity.createUsuarioPaciente
import com.example.gsi.databinding.ActivityRegisterBinding
import java.util.regex.Matcher
import java.util.regex.Pattern


class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Constant.api.getAllPais(this, binding)
        Constant.api.getAllEstadoCivil(this, binding)
        Constant.api.getAllSexo(this, binding)
        binding.txtInputEmail.isErrorEnabled = false
        binding.txtLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.editTextTexNombre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.toString().isNotEmpty()) {
                    binding.txtInputNombre.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.txtInputNombre.error = "Ingrese su Nombre"
                    binding.txtInputNombre.isErrorEnabled = true
                }
            }
        })
        binding.ediTextApePaterno.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.toString().isNotEmpty()) {
                    binding.txtInputApePaterno.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.txtInputApePaterno.error = "Debe ingresar su Apellido Paterno"
                    binding.txtInputApePaterno.isErrorEnabled = true
                }
            }
        })
        binding.ediTextApeMaterno.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.toString().isNotEmpty()) {
                    binding.txtInputApeMaterno.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.txtInputApeMaterno.error = "Debe ingresar su Apellido Materno"
                    binding.txtInputApeMaterno.isErrorEnabled = true
                }
            }
        })
        binding.editTextDni.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.toString().isNotEmpty()) {
                    binding.txtInputDni.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.txtInputDni.error = "Debe ingresar su Dni"
                    binding.txtInputDni.isErrorEnabled = true
                }
            }
        })
        binding.editTextDireccion.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.toString().isNotEmpty()) {
                    binding.txtInputDireccion.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.txtInputDireccion.error = "Debe ingresar su Direccion"
                    binding.txtInputDireccion.isErrorEnabled = true
                }
            }
        })
        binding.editTextTelefono.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.toString().isNotEmpty()) {
                    binding.txtInputTelefono.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.txtInputTelefono.error = "Debe ingresar su Telefono"
                    binding.txtInputTelefono.isErrorEnabled = true
                }
            }
        })
        binding.editTexEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {

                if (s.toString().isNotEmpty()) {
                    binding.txtInputEmail.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()
                ) {
                    binding.txtInputEmail.error = "Debe ingresar su Email"
                    binding.txtInputEmail.isErrorEnabled = true
                }else if (binding.editTexEmail.text.toString().matches(("[a-zA-Z0-9._-]+@[a-z]+\\.[a-z]+").toString().toRegex())){
                }else{
                    binding.txtInputEmail.isErrorEnabled = true
                    binding.txtInputEmail.error = "Email invalido"
                }

            }
        })

        binding.editTextPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.toString().isNotEmpty()) {
                    binding.txtInputPassword.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.txtInputPassword.error = "Debe ingresar Contraseña"
                    binding.txtInputPassword.isErrorEnabled = true
                }
            }
        })
        binding.editTextTexConfirPass.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.toString().isNotEmpty()) {
                    binding.txtInputConfirPass.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.txtInputConfirPass.error = "Debe confirmar su Contraseña"
                    binding.txtInputConfirPass.isErrorEnabled = true
                }
            }
        })

        binding.btnRegistrate.setOnClickListener {

            val usuario = createUsuarioPaciente(
                binding.editTextDni.text.toString(), binding.editTextDni.text.toString(),
                createRolUsuario(1)
            )
            Log.e("pais",(binding.spPais.selectedItemPosition.toLong()).toString())
            if (binding.editTextTexNombre.text.toString().isEmpty()) {
                binding.editTextTexNombre.requestFocus()
                binding.txtInputNombre.error = "Ingrese su Nombre"
                binding.txtInputNombre.isErrorEnabled = true
            } else if (binding.ediTextApePaterno.text.toString().isEmpty()) {
                binding.ediTextApePaterno.requestFocus()
                binding.txtInputApePaterno.error = "Debe ingresar su Apellido Paterno"
            } else if (binding.ediTextApeMaterno.text.toString().isEmpty()) {
                binding.ediTextApeMaterno.requestFocus()
                binding.txtInputApePaterno.error = "Debe ingresar su Apellido Materno"
            } else if (binding.editTextDni.text.toString().isEmpty()) {
                binding.editTextDni.requestFocus()
                binding.txtInputDni.error = "Debe ingresar su Dni"
            } else if (binding.editTextDireccion.text.toString().isEmpty()) {
                binding.editTextDireccion.requestFocus()
                binding.txtInputDireccion.error = "Debe ingresar su Direccion"
            } else if (binding.editTextTelefono.text.toString().isEmpty()) {
                binding.editTextTelefono.requestFocus()
                binding.txtInputTelefono.error = "Debe ingresar su Direccion"
            } else if (binding.editTexEmail.text.toString().isEmpty()) {
                binding.editTexEmail.requestFocus()
                binding.txtInputEmail.error = "Debe ingresar su Email"
            } else if (binding.editTextPassword.text.toString().isEmpty()) {
                binding.editTextPassword.requestFocus()
                binding.txtInputPassword.error = "Debe ingresar su Contraseña"
            } else if (binding.editTextTexConfirPass.text.toString().isEmpty()) {
                binding.editTextTexConfirPass.requestFocus()
                binding.txtInputConfirPass.error = "Debe confirmar su Contraseña"
            } else if (binding.editTextPassword.text.toString() != binding.editTextTexConfirPass.text.toString()) {
                binding.editTextTexConfirPass.requestFocus()
                binding.txtInputConfirPass.error = "La contraseña no concuerda"
            }else if(binding.spPais.selectedItemPosition==0){
                binding.spPais.requestFocusFromTouch()
            }else if(binding.spEstadoCivil.selectedItemPosition==0){
                binding.spEstadoCivil.requestFocusFromTouch()
            }
            else if(binding.spSexo.selectedItemPosition==0){
                binding.spSexo.requestFocusFromTouch()
            }
            else {
                Constant.api.createUsuarioPacienteControlSalud(binding,usuario)
            }


        }
        //toolbar
        binding.customPrinciapl.btnRegresar.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}