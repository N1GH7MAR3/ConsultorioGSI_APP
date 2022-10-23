package com.example.gsi

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.gsi.databinding.ActivityDashboardAdminBinding

class DashboardAdminActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDashboardAdminBinding
    lateinit var cardEspecialidad: CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityDashboardAdminBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val nombre = intent.getStringExtra("nombre")
        binding.txtNombre.text = nombre


        }

    
}