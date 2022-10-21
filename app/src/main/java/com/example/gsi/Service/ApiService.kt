package com.example.gsi.Service

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.service.autofill.UserData
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import com.example.gsi.DashboardPacienteActivity
import com.example.gsi.Entity.Usuario
import com.example.gsi.RegisterActivity
import com.example.gsi.Retrofit.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ApiService {

     fun VerifyUser(activity: Activity, usuario:String, contraseña:String) {
        val retrofit=RetrofitHelper.getRetrofit().create(Services::class.java)
        val user=Usuario(usuario,contraseña)
        retrofit.VerifyUser(user).enqueue(
            object:Callback<Usuario>{
                override fun onResponse(
                    call: Call<Usuario>,
                    response: Response<Usuario>) {

                    if (response.code()==302){
                        val intent=Intent(activity,DashboardPacienteActivity::class.java)
                        activity.startActivity(intent)
                    }else if(response.code()==404){
                        Toast.makeText(activity,"Usuario o Contraseña Incorrecto",Toast.LENGTH_LONG).show()
                    }
                    }
                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Toast.makeText(activity,"Error",Toast.LENGTH_LONG).show()

                }

            }
        )



    }
}