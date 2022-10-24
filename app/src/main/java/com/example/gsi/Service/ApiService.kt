package com.example.gsi.Service

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gsi.Adapter.EspecialidadAdapter
import com.example.gsi.Adapter.EspecialidadAdminAdapter
import com.example.gsi.Constans.Constant
import com.example.gsi.DashboardAdminActivity
import com.example.gsi.DashboardPacienteActivity
import com.example.gsi.Entity.*
import com.example.gsi.EspecialidadesAdminActivity
import com.example.gsi.EspecialidadesPacienteActivity
import com.example.gsi.databinding.ActivityEspecialidadesAdminBinding
import com.example.gsi.databinding.ActivityEspecialidadesPacienteBinding
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@OptIn(DelicateCoroutinesApi::class)
open class ApiService {
    var c1 = GlobalScope.launch { }
    var c2 = GlobalScope.launch { }
    var c3 = GlobalScope.launch { }
    var c4 = GlobalScope.launch { }
    var c5 = GlobalScope.launch { }
    fun verifyUser(
        activity: Activity,
        usuario: String,
        contraseña: String,
        username: EditText,
        password: EditText
    ) {
        c1 = GlobalScope.launch(Dispatchers.Main) {
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
                        } else if (response.body()?.rol?.nombre.toString() == "admin") {
                            val intent = Intent(activity, DashboardAdminActivity::class.java)
                            intent.putExtra("nombre", response.body()?.usuario)
                            activity.startActivity(intent)
                            c1.cancel()
                        } else {

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
        c2 = GlobalScope.launch(Dispatchers.Main) {
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

    fun getEspecilidadesPaciente(
        activity: EspecialidadesPacienteActivity,
        binding: ActivityEspecialidadesPacienteBinding
    ) {
        c3 = GlobalScope.launch(Dispatchers.Main) {
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
                        c3.cancel()
                    }
                }

                override fun onFailure(call: Call<List<Especialidad>>, t: Throwable) {
                    Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
                }
            })
        }
    }


    fun getEspecilidadesAdmin(
        activity: EspecialidadesAdminActivity,
        binding: ActivityEspecialidadesAdminBinding
    ) {
        c4 = GlobalScope.launch(Dispatchers.Main) {
            fun iniRecyclerView(list: List<Especialidad>) {
                binding.rvEspecialidades.layoutManager =
                    LinearLayoutManager(activity.applicationContext)
                binding.rvEspecialidades.adapter = EspecialidadAdminAdapter(list)
            }
            Constant.retrofit.getAllEspecialidades().enqueue(object : Callback<List<Especialidad>> {
                override fun onResponse(
                    call: Call<List<Especialidad>>,
                    response: Response<List<Especialidad>>
                ) {
                    activity.runOnUiThread {
                        val list: List<Especialidad> = response.body()!!
                        iniRecyclerView(list)
                        c4.cancel()
                    }
                }

                override fun onFailure(call: Call<List<Especialidad>>, t: Throwable) {
                    Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    fun updateEspecialidad(id: Long, especialidad: Especialidad, activity: Activity) {
        Constant.retrofit.updateEspecialidad(id, especialidad)
            .enqueue(object : Callback<Especialidad> {
                override fun onResponse(
                    call: Call<Especialidad>,
                    response: Response<Especialidad>
                ) {
                    Toast.makeText(activity, "Se ha Editado la Especialidad", Toast.LENGTH_SHORT)
                        .show()
                    val intent =
                        Intent(activity, DashboardAdminActivity::class.java)
                    activity.startActivity(intent)
                }

                override fun onFailure(call: Call<Especialidad>, t: Throwable) {
                    Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
                }
            })
    }

    fun deleteEspecialidad(id: Long,context: Context) {
        Constant.retrofit.deleteEspecialidad(id).enqueue(object :Callback<Especialidad>{
            override fun onResponse(call: Call<Especialidad>, response: Response<Especialidad>) {
                Toast.makeText(context,"Se ha eliminado la Especialidad",Toast.LENGTH_SHORT).show()
                val intent =
                    Intent(context.applicationContext, DashboardAdminActivity::class.java)
                context.applicationContext.startActivity(intent)

            }
            override fun onFailure(call: Call<Especialidad>, t: Throwable) {
                Toast.makeText(context, Constant.NoInternet, Toast.LENGTH_LONG).show()
            }
        })
    }
    fun createEspecialidad(especialidad: createEspecialidad,activity: Activity){
        Constant.retrofit.createEspecialidad(especialidad).enqueue(object :Callback<createEspecialidad>{
            override fun onResponse(
                call: Call<createEspecialidad>,
                response: Response<createEspecialidad>
            ) {
                val intent =
                    Intent(activity, DashboardAdminActivity::class.java)
                activity.startActivity(intent)
                activity.finish()
            }

            override fun onFailure(call: Call<createEspecialidad>, t: Throwable) {
                Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()

            }

        })
    }
}





