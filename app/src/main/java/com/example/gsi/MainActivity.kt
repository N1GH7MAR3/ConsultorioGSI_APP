package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.Toast
import com.example.gsi.Entity.Alergia

import com.example.gsi.Retrofit.RetrofitHelper
import com.example.gsi.Service.Services
import com.example.gsi.databinding.ActivityDashboardPacienteBinding
import com.example.gsi.databinding.ActivityMainBinding
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}