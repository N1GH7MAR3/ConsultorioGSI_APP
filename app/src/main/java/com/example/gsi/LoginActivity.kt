package com.example.gsi

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityLoginBinding
import java.lang.Thread.*


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = binding.editTextEmailLogin
        val password = binding.editTextPasswordLogin
        binding.txtInputUsuario.requestFocus()
        username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s.toString().isNotEmpty()) {
                    binding.txtInputUsuario.isErrorEnabled = false
                }
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.txtInputUsuario.error = "Ingrese un Usuario"
                    binding.txtInputUsuario.isErrorEnabled = true
                }
            }
        })
        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (s.toString().isNotEmpty()) {
                    binding.txtInputPassword.isErrorEnabled = false
                }

            }

            override fun afterTextChanged(s: Editable?) {
                if (s.toString().isEmpty()) {
                    binding.txtInputPassword.error = "Ingrese su Contraseña"
                    binding.txtInputPassword.isErrorEnabled = true
                }
            }
        })

        binding.btnLogin.setOnClickListener {
            if (username.text.toString() == "") {
                binding.txtInputUsuario.error = "Ingrese un Usuario"
                username.requestFocus()
            } else if (password.text.toString() == "") {
                binding.txtInputPassword.error = "Ingres su Contraseña"
                password.requestFocus()
            } else {
                Constant.api.verifyUser(
                    binding,
                    username,
                    password
                )


            }
        }
        binding.btnInvitado.setOnClickListener {
            val intent = Intent(this@LoginActivity, DashboardInvitadoActivity::class.java)
            startActivity(intent)
            finish()
        }
        binding.txtNuevoUsuario.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)


        }


    }








}


