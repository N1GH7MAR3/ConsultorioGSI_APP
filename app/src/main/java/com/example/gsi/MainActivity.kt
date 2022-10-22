package com.example.gsi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.Toast
import com.example.gsi.Entity.Alergia

import com.example.gsi.Retrofit.RetrofitHelper
import com.example.gsi.Service.Services
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val boton=findViewById<Button>(R.id.hola)
    //    boton.setOnClickListener { v-> getAllAlergias()}

    }
    //private fun getAllAlergias(){
    //    val alergiaService:Services=RetrofitHelper.getRetrofit().create(Services::class.java)
    //    val result =alergiaService.getAllAlergias()
    //    result.enqueue(object :Callback<List<Alergia>>{
    //        override fun onFailure(call: retrofit2.Call<List<Alergia>>, t: Throwable) {
    //            Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_LONG).show()
    //        }
    //        override fun onResponse(
    //            call: retrofit2.Call<List<Alergia>>,
    //            response: Response<List<Alergia>>
    //        ) {
    //            Toast.makeText(this@MainActivity,"OK",Toast.LENGTH_LONG).show()
    //        }
    //    })

    //}


}