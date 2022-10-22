package com.example.gsi.Service

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.service.autofill.UserData
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import com.example.gsi.Constans.Constant
import com.example.gsi.DashboardPacienteActivity
import com.example.gsi.Entity.*
import com.example.gsi.RegisterActivity
import com.example.gsi.Retrofit.RetrofitHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ApiService {

     fun verifyUser(activity: Activity, usuario:String, contraseña:String) {
        val user=ValidateUsuario(usuario,contraseña)
        Constant.retrofit.verifyUser(user).enqueue(
            object:Callback<Usuario>{
                override fun onResponse(
                    call: Call<Usuario>,
                    response: Response<Usuario>) {
                    if(response.isSuccessful){
                        if(response.body()?.contraseña.toString()!=contraseña){
                            Toast.makeText(activity,"Contraseña Incorrecta",Toast.LENGTH_LONG).show()
                        }else{
                            searchPaciente(activity,usuario)


                        }

                    }else if(response.code()==404){
                        Toast.makeText(activity,"Usuario no Registrado",Toast.LENGTH_LONG).show()
                    }
                }

                    override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Toast.makeText(activity,Constant.NoInternet,Toast.LENGTH_LONG).show()

                }

            }
        )

    }
    fun searchPaciente(activity: Activity,usuario:String){
        val pac=SearchUsuario(usuario)
        Constant.retrofit.searchPaciente(pac).enqueue(
            object:Callback<Paciente>{
                override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                    if (response.isSuccessful){
                        val intent=Intent(activity,DashboardPacienteActivity::class.java)
                        intent.putExtra("nombre",response.body()?.nombre)
                        activity.startActivity(intent)
                        activity.finish()
                    }
                }

                override fun onFailure(call: Call<Paciente>, t: Throwable) {
                    Toast.makeText(activity,Constant.NoInternet,Toast.LENGTH_LONG).show()
                }

            }
        )
    }
}