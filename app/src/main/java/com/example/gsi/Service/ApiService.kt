package com.example.gsi.Service

import android.app.Activity
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gsi.Adapter.EspecialidadAdapter
import com.example.gsi.Constans.Constant
import com.example.gsi.DashboardAdminActivity
import com.example.gsi.DashboardPacienteActivity
import com.example.gsi.Entity.*
import com.example.gsi.EspecialidadesActivity
import com.example.gsi.databinding.ActivityEspecialidadesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ApiService {
    var c1= GlobalScope.launch {  }
    var c2= GlobalScope.launch {  }
    fun verifyUser(
        activity: Activity,
        usuario: String,
        contraseña: String,
        username: EditText,
        password: EditText
    ) {
        c1=GlobalScope.launch(Dispatchers.Main){
            val user = ValidateUsuario(usuario, contraseña)
            Constant.retrofit.verifyUser(user).enqueue(
                object : Callback<Usuario> {
                    override fun onResponse(
                        call: Call<Usuario>,
                        response: Response<Usuario>
                    ) {

                        if (response.code() == 404) {
                            Toast.makeText(activity, "Usuario no Registrado", Toast.LENGTH_LONG)
                                .show()
                        } else if (response.body()?.contraseña.toString() != contraseña) {
                            Toast.makeText(
                                activity,
                                "Contraseña Incorrecta",
                                Toast.LENGTH_LONG
                            ).show()
                        }else if(response.body()?.rol?.nombre.toString()=="admin"){
                            val intent = Intent(activity, DashboardAdminActivity::class.java)
                            intent.putExtra("nombre", response.body()?.usuario)
                            activity.startActivity(intent)
                            c1.cancel()
                        }
                        else {

                                c1.cancel()
                                username.setText("")
                                password.setText("")
                                searchPaciente(activity, usuario)


                        }
                    }
                    override fun onFailure(call: Call<Usuario>, t: Throwable) {
                        Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
                    }

        }
        )
    }
}

fun searchPaciente(activity: Activity, usuario: String) {
    c1=GlobalScope.launch(Dispatchers.Main){
        val pac = SearchUsuario(usuario)
        Constant.retrofit.searchPaciente(pac).enqueue(
            object : Callback<Paciente> {
                override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {

                        if (response.isSuccessful) {
                            val intent = Intent(activity, DashboardPacienteActivity::class.java)
                            intent.putExtra("nombre", response.body()?.nombre)
                            activity.startActivity(intent)
                            c2.cancel()


                    }
                }
                override fun onFailure(call: Call<Paciente>, t: Throwable) {
                    Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
                }
            }
        )
    }

}

fun getEspecilidades(activity: EspecialidadesActivity, binding: ActivityEspecialidadesBinding) {
    CoroutineScope(Dispatchers.IO).launch {
        fun iniRecyclerView(list: List<Especialidad>) {
            binding.rvEspecialidades.layoutManager =
                LinearLayoutManager(activity.applicationContext)
            binding.rvEspecialidades.adapter = EspecialidadAdapter(list)
        }
        Constant.retrofit.getAllEspecialidades().enqueue(object : Callback<List<Especialidad>> {
            override fun onResponse(
                call: Call<List<Especialidad>>,
                response: Response<List<Especialidad>>
            ) {
                activity.runOnUiThread {
                val list: List<Especialidad> = response.body()!!
                iniRecyclerView(list)
                }
            }

            override fun onFailure(call: Call<List<Especialidad>>, t: Throwable) {
            }
        })
    }
}

}