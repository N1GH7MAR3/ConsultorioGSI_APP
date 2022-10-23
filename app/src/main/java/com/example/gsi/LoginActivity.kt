package com.example.gsi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.gsi.Constans.Constant
import com.example.gsi.Service.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginActivity : AppCompatActivity() {
    lateinit var txtUsuario: EditText
    lateinit var txtPassword: EditText
    lateinit var txtNuevoUsuario: TextView
    lateinit var btnInvitado: Button
    lateinit var btnLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        txtNuevoUsuario = findViewById(R.id.txtNuevoUsuario)
        btnInvitado = findViewById(R.id.btnInvitado)
        btnLogin = findViewById(R.id.btnLogin)
        txtUsuario = findViewById(R.id.editTextEmailLogin)
        txtPassword = findViewById(R.id.editTextPasswordLogin)

        btnInvitado.setOnClickListener {

            //val intent = Intent(this@LoginActivity,DashboardInvitadoActivity::class.java)
            //startActivity(intent)
            //finish()
        }
        btnLogin.setOnClickListener {
            val usuario = txtUsuario.text.toString()
            val password = txtPassword.text.toString()
            if (usuario.equals("")) {
                txtUsuario.requestFocus()
                Toast.makeText(this@LoginActivity, "Ingrese un Correo o Usuario", Toast.LENGTH_LONG)
                    .show()
            } else if (password.equals("")) {
                txtPassword.requestFocus()
                Toast.makeText(this@LoginActivity, "Ingrese una Contraseña", Toast.LENGTH_LONG)
                    .show()
            } else {
                Constant.api.verifyUser(this@LoginActivity, usuario, password)
            }
        }
        txtNuevoUsuario.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


}