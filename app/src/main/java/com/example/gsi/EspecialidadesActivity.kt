package com.example.gsi


import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.recyclerview.widget.LinearLayoutManager

import com.example.gsi.Adapter.EspecialidadAdapter
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.Especialidad

import com.example.gsi.databinding.ActivityEspecialidadesBinding

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class EspecialidadesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEspecialidadesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEspecialidadesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Constant.api.getEspecilidades(this@EspecialidadesActivity,binding)
    }




}