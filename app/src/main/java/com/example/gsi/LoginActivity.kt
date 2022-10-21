package com.example.gsi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    lateinit var txtNuevoUsuario: TextView
    lateinit var btnInvitado: Button
    lateinit var btnLogin: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtNuevoUsuario = findViewById(R.id.txtNuevoUsuario)
        btnInvitado= findViewById(R.id.btnInvitado)
        btnLogin= findViewById(R.id.btnLogin)

        btnInvitado.setOnClickListener {
            val intent = Intent(this@LoginActivity,DashboardInvitadoActivity::class.java)
            startActivity(intent)
            finish()
        }
        btnLogin.setOnClickListener {
            val intent = Intent(this@LoginActivity,DashboardPacienteActivity::class.java)
            startActivity(intent)
            finish()
        }
        txtNuevoUsuario.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}