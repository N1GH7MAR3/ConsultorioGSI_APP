package com.example.gsi

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gsi.Constans.Constant
import com.example.gsi.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val username = binding.editTextEmailLogin
        val password = binding.editTextPasswordLogin
        binding.btnInvitado.setOnClickListener {

            //val intent = Intent(this@LoginActivity,DashboardInvitadoActivity::class.java)
            //startActivity(intent)
            //finish()
        }
        binding.btnLogin.setOnClickListener {
            if (username.text.toString() == "" && password.text.toString() == "") {
                Toast.makeText(
                    this@LoginActivity,
                    "Ingrese su Usuario y contraseña",
                    Toast.LENGTH_LONG
                )
                    .show()
                username.requestFocus()

            } else if (password.text.toString() == "") {
                binding.txtInputPassword.requestFocus()
                Toast.makeText(this@LoginActivity, "Ingrese una Contraseña", Toast.LENGTH_LONG)
                    .show()
            } else {

                    Constant.api.verifyUser(
                        this@LoginActivity,
                        username.text.toString(),
                        password.text.toString(), username, password
                    )


            }
        }
        binding.txtNuevoUsuario.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }


    }


}


