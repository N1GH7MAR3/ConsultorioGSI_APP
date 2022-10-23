package com.example.gsi


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
    private  var list: List<Especialidad> = mutableListOf<Especialidad>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEspecialidadesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getEspecilidades()
    }


    private fun getEspecilidades() {
        CoroutineScope(Dispatchers.IO).launch {
            Constant.retrofit.getAllEspecialidades().enqueue(object : Callback<List<Especialidad>> {
                override fun onResponse(
                    call: Call<List<Especialidad>>,
                    response: Response<List<Especialidad>>
                ) {
                    val list : List<Especialidad> = response.body()!!
                    iniRecyclerView(list)
                }
                override fun onFailure(call: Call<List<Especialidad>>, t: Throwable) {
                }
            })
        }
    }

    fun iniRecyclerView(list: List<Especialidad>) {
        binding.rvEspecialidades.layoutManager = LinearLayoutManager(this)
        binding.rvEspecialidades.adapter = EspecialidadAdapter(list)
    }
}