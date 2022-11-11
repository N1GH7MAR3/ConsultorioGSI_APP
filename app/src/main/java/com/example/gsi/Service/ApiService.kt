package com.example.gsi.Service

import android.R
import android.app.Activity
import android.content.Context
import android.content.Entity
import android.content.Intent
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gsi.*
import com.example.gsi.Adapter.*
import com.example.gsi.Constans.Constant
import com.example.gsi.Entity.*
import com.example.gsi.databinding.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class ApiService {

    //Verificar Existencia del Usuario
    fun verifyUser(
        activity: Activity,
        usuario: String,
        contraseña: String,
        username: EditText,
        password: EditText
    ) {

        val user = ValidateUsuario(usuario, contraseña)
        Constant.retrofit.verifyUser(user).enqueue(
            object : Callback<Usuario> {
                override fun onResponse(
                    call: Call<Usuario>,
                    response: Response<Usuario>
                ) {
                    activity.runOnUiThread {
                        if (response.code() == 404) {
                            Toast.makeText(activity, "Usuario no Registrado", Toast.LENGTH_LONG)
                                .show()
                            call.cancel()
                        } else if (response.body()?.contraseña.toString() != contraseña) {
                            Toast.makeText(
                                activity,
                                "Contraseña Incorrecta",
                                Toast.LENGTH_LONG
                            ).show()
                            call.cancel()
                        } else if (response.body()?.rol?.nombre.toString() == "admin") {
                            Toast.makeText(
                                activity,
                                "Ingreso satisfactorio con login",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(activity, DashboardAdminActivity::class.java)
                            intent.putExtra("nombre", response.body()?.usuario)
                            activity.startActivity(intent)
                            activity.finish()
                            call.cancel()
                        } else {
                            username.setText("")
                            password.setText("")
                            Toast.makeText(
                                activity,
                                "Ingreso satisfactorio con login",
                                Toast.LENGTH_SHORT
                            ).show()
                            searchPaciente(activity, usuario)
                            call.cancel()
                        }
                    }
                }

                override fun onFailure(call: Call<Usuario>, t: Throwable) {
                    Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
                }
            }
        )
    }


    //Buscar Paciente para obtener nombre y dni
    fun searchPaciente(activity: Activity, usuario: String) {
        val pac = SearchUsuario(usuario)
        Constant.retrofit.searchPaciente(pac).enqueue(
            object : Callback<Paciente> {
                override fun onResponse(call: Call<Paciente>, response: Response<Paciente>) {
                    activity.runOnUiThread {
                        if (response.isSuccessful) {
                            val intent = Intent(activity, DashboardPacienteActivity::class.java)
                            intent.putExtra("dni", response.body()?.dni.toString())
                            intent.putExtra("nombre", response.body()?.nombre)
                            intent.putExtra("enfermedad",response.body()?.enfermedad?.descripcion)
                            activity.startActivity(intent)
                            //activity.finish()
                            call.cancel()
                        }
                    }
                }

                override fun onFailure(call: Call<Paciente>, t: Throwable) {
                    Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
                }
            }
        )
    }


    //Obtener todas las especialidades
    fun getEspecilidadesPaciente(
        activity: EspecialidadesPacienteActivity,
        binding: ActivityEspecialidadesPacienteBinding
    ) {
        fun iniRecyclerView(list: List<Especialidad>) {
            binding.rvEspecialidades.layoutManager =
                LinearLayoutManager(activity.applicationContext)
            binding.rvEspecialidades.adapter = EspecialidadAdapter(list)
        }
        Constant.retrofit.getAllEspecialidades()
            .enqueue(object : Callback<List<Especialidad>> {
                override fun onResponse(
                    call: Call<List<Especialidad>>,
                    response: Response<List<Especialidad>>
                ) {
                    activity.runOnUiThread {
                        val list: List<Especialidad> = response.body()!!
                        iniRecyclerView(list)
                        call.cancel()
                    }
                }

                override fun onFailure(call: Call<List<Especialidad>>, t: Throwable) {
                    Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
                }
            })
    }


    //Obtener todos los medicos
    fun getAllMedico(
        activity: MedicosAdminActivity,
        binding: ActivityMedicosAdminBinding
    ) {
        fun iniRecyclerView(list: List<Medico>) {
            binding.rvMedico.layoutManager =
                LinearLayoutManager(activity.applicationContext)
            binding.rvMedico.adapter = MedicoAdminAdapter(list)
        }
        Constant.retrofit.getAllMedico().enqueue(object : Callback<List<Medico>> {
            override fun onResponse(
                call: Call<List<Medico>>,
                response: Response<List<Medico>>
            ) {
                activity.runOnUiThread {
                    val list: List<Medico> = response.body()!!
                    iniRecyclerView(list)
                    call.cancel()
                }
            }

            override fun onFailure(call: Call<List<Medico>>, t: Throwable) {
                Log.e("hola", t.toString())
            }
        })
    }


    //Obtener las especialidades para la vista del Admin
    fun getEspecilidadesAdmin(
        activity: EspecialidadesAdminActivity,
        binding: ActivityEspecialidadesAdminBinding
    ) {
        fun iniRecyclerView(list: List<Especialidad>) {
            binding.rvEspecialidades.layoutManager =
                LinearLayoutManager(activity.applicationContext)
            binding.rvEspecialidades.adapter = EspecialidadAdminAdapter(list)
        }
        Constant.retrofit.getAllEspecialidades()
            .enqueue(object : Callback<List<Especialidad>> {
                override fun onResponse(
                    call: Call<List<Especialidad>>,
                    response: Response<List<Especialidad>>
                ) {
                    activity.runOnUiThread {
                        val list: List<Especialidad> = response.body()!!
                        iniRecyclerView(list)
                        call.cancel()

                    }
                }

                override fun onFailure(call: Call<List<Especialidad>>, t: Throwable) {
                    Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
                }
            })

    }

    //Crear Especialidad
    fun createEspecialidad(especialidad: createEspecialidad, context: Context) {
        val call = Constant.retrofit.createEspecialidad(especialidad)
            .enqueue(object : Callback<createEspecialidad> {
                override fun onResponse(
                    call: Call<createEspecialidad>,
                    response: Response<createEspecialidad>
                ) {
                    (context as Activity).runOnUiThread {
                        val intent =
                            Intent(context, EspecialidadesAdminActivity::class.java)
                        Thread.sleep(3000)
                        context.startActivity(intent)
                        context.finish()
                        call.cancel()
                    }
                }

                override fun onFailure(call: Call<createEspecialidad>, t: Throwable) {
                    Toast.makeText(context, Constant.NoInternet, Toast.LENGTH_LONG).show()

                }

            })
    }


    //Editar Especialidad
    fun updateEspecialidad(id: Long, especialidad: Especialidad, activity: Activity) {
        val call = Constant.retrofit.updateEspecialidad(id, especialidad)
            .enqueue(object : Callback<Especialidad> {
                override fun onResponse(
                    call: Call<Especialidad>,
                    response: Response<Especialidad>
                ) {
                    activity.runOnUiThread {
                        Toast.makeText(
                            activity,
                            "Se ha Editado la Especialidad",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                        val intent =
                            Intent(activity, EspecialidadesAdminActivity::class.java)
                        activity.startActivity(intent)
                        activity.finish()
                    }
                }

                override fun onFailure(call: Call<Especialidad>, t: Throwable) {
                    Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
                }
            })
    }


    //Eliminar Especialidad
    fun deleteEspecialidad(id: Long, activity: Activity) {

        Constant.retrofit.deleteEspecialidad(id).enqueue(object : Callback<Especialidad> {
            override fun onResponse(
                call: Call<Especialidad>,
                response: Response<Especialidad>
            ) {
                activity.runOnUiThread {
                    Toast.makeText(
                        activity,
                        "Se ha eliminado la Especialidad",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                    val intent =
                        Intent(
                            activity.applicationContext,
                            EspecialidadesAdminActivity::class.java
                        )
                    Thread.sleep(3000)
                    activity.finish()
                    activity.startActivity(intent)
                    call.cancel()
                }
            }

            override fun onFailure(call: Call<Especialidad>, t: Throwable) {
                Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
            }
        })
    }


    //Obtener Citas del Paciente
    fun getCitasPaciente(
        dni: Int, activity: CitaPacienteActivity,
        binding: ActivityCitaPacienteBinding
    ) {
        fun iniRecyclerView(list: List<Cita>) {
            binding.rvCita.layoutManager =
                LinearLayoutManager(activity.applicationContext)
            binding.rvCita.adapter = CitaPacienteAdapter(list)
        }
        Constant.retrofit.getCitasPaciente(dni).enqueue(object : Callback<List<Cita>> {
            override fun onResponse(call: Call<List<Cita>>, response: Response<List<Cita>>) {
                activity.runOnUiThread {
                    val list: List<Cita> = response.body()!!
                    iniRecyclerView(list)
                    call.cancel()
                }
            }

            override fun onFailure(call: Call<List<Cita>>, t: Throwable) {
                Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
            }

        })
    }


    //Crear Medico
    fun createMedico(medico: createMedico, activity: Activity) {
        Constant.retrofit.createMedico(medico).enqueue(object : Callback<createMedico> {
            override fun onResponse(call: Call<createMedico>, response: Response<createMedico>) {
                Toast.makeText(activity, "MEDICO Creado", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<createMedico>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    //Obtener los procedimientos por especialidad
    fun getProcedimientoxEspecialidad(
        nombre: String, activity: ProcedimientoPacienteActivity,
        binding: ActivityProcedimientoPacienteBinding
    ) {
        fun iniRecyclerView(list: List<Procedimiento>) {
            binding.rvProcedimiento.layoutManager =
                LinearLayoutManager(activity.applicationContext)
            binding.rvProcedimiento.adapter = ProdecimientoPacienteAdapter(list)
        }
        Constant.retrofit.getProcedimientoxEspecialidad(nombre)
            .enqueue(object : Callback<List<Procedimiento>> {
                override fun onResponse(
                    call: Call<List<Procedimiento>>,
                    response: Response<List<Procedimiento>>
                ) {

                    val list: List<Procedimiento> = response.body()!!
                    iniRecyclerView(list)
                    call.cancel()

                }

                override fun onFailure(call: Call<List<Procedimiento>>, t: Throwable) {
                    Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()

                }

            })
    }

    //Obtener Medicos para la vista del Paciente
    fun getAllMedicoPaciente(
        nombre: String, nombreprocedimiento: String,
        activity: MedicoPacienteActivity,
        binding: ActivityMedicoPacienteBinding
    ) {
        fun iniRecyclerView(list: List<Medico>, nombreprocedimiento: String) {
            binding.rvMedico.layoutManager =
                LinearLayoutManager(activity.applicationContext)
            binding.rvMedico.adapter = MedicoPacienteAdapter(list, nombreprocedimiento)
        }
        Constant.retrofit.getMedicoxEspecialidad(nombre).enqueue(object :
            Callback<List<Medico>> {
            override fun onResponse(
                call: Call<List<Medico>>,
                response: Response<List<Medico>>
            ) {
                activity.runOnUiThread {
                    val list: List<Medico> = response.body()!!
                    iniRecyclerView(list, nombreprocedimiento)
                    call.cancel()
                }
            }

            override fun onFailure(call: Call<List<Medico>>, t: Throwable) {
                Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
            }
        })

    }

    //Obtener los paises
    fun getAllPais(activity: Activity, binding: ActivityRegisterBinding) {
        Constant.retrofit.getAllPais().enqueue(object : Callback<List<Pais>> {
            override fun onResponse(call: Call<List<Pais>>, response: Response<List<Pais>>) {
                val list = mutableListOf<String>()
                val listPais = response.body()
                for (i in listPais!!.indices) list += listPais[i].nombre
                binding.spPais.adapter = ArrayAdapter(
                    activity,
                    R.layout.simple_spinner_dropdown_item,
                    list

                )
            }

            override fun onFailure(call: Call<List<Pais>>, t: Throwable) {
                Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()

            }

        })
    }

    //Obtener el estadoCivil
    fun getAllEstadoCivil(activity: Activity, binding: ActivityRegisterBinding) {
        Constant.retrofit.getAllEstadoCivil().enqueue(object : Callback<List<EstadoCivil>> {
            override fun onResponse(
                call: Call<List<EstadoCivil>>,
                response: Response<List<EstadoCivil>>
            ) {
                val list = mutableListOf<String>()
                val listEstadoCivil = response.body()
                for (i in listEstadoCivil!!.indices) list += listEstadoCivil[i].nombre
                binding.spEstadoCivil.adapter = ArrayAdapter(
                    activity,
                    R.layout.simple_spinner_dropdown_item,
                    list
                )
            }

            override fun onFailure(call: Call<List<EstadoCivil>>, t: Throwable) {
                Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
            }

        })
    }

    //Obtener el Sexo

    fun getAllSexo(activity: Activity, binding: ActivityRegisterBinding) {
        Constant.retrofit.getAllSexo().enqueue(object : Callback<List<Sexo>> {
            override fun onResponse(call: Call<List<Sexo>>, response: Response<List<Sexo>>) {
                val list = mutableListOf<String>()
                val listSexo = response.body()
                for (i in listSexo!!.indices) list += listSexo[i].nombre
                binding.spSexo.adapter = ArrayAdapter(
                    activity,
                    R.layout.simple_spinner_dropdown_item,
                    list
                )

            }

            override fun onFailure(call: Call<List<Sexo>>, t: Throwable) {
                Toast.makeText(activity, Constant.NoInternet, Toast.LENGTH_LONG).show()
            }

        })
    }



    fun createUsuarioPacienteControlSalud(
        binding: ActivityRegisterBinding,
        usuarioPaciente: createUsuarioPaciente,

        ) {
        Constant.retrofit.createContactoEmergencia(com.example.gsi.Entity.createContactoEmergencia(""))
            .enqueue(object : Callback<ContactoEmergencia> {
                override fun onResponse(
                    call: Call<ContactoEmergencia>,
                    response: Response<ContactoEmergencia>
                ) {
                    val idCE = putContactoEmergencia(response.body()?.id!!)
                    Constant.retrofit.createContactoMedico(
                        com.example.gsi.Entity.createContactoMedico(
                            ""
                        )
                    ).enqueue(object : Callback<ContactoMedico> {
                        override fun onResponse(
                            call: Call<ContactoMedico>,
                            response: Response<ContactoMedico>
                        ) {
                            val idCM = putContactoMedico(response.body()?.id!!)
                            Constant.retrofit.createEnfermedad(
                                com.example.gsi.Entity.createEnfermedad(
                                    ""
                                )
                            ).enqueue(object : Callback<Enfermedad> {
                                override fun onResponse(
                                    call: Call<Enfermedad>,
                                    response: Response<Enfermedad>
                                ) {
                                    val idE = putEnfermedad(response.body()?.id!!)
                                    Constant.retrofit.createMedicina(
                                        com.example.gsi.Entity.createMedicina(
                                            ""
                                        )
                                    ).enqueue(object : Callback<Medicina> {
                                        override fun onResponse(
                                            call: Call<Medicina>,
                                            response: Response<Medicina>
                                        ) {
                                            val idM = putMedicina(response.body()?.id!!)
                                            Constant.retrofit.createAlergia(
                                                com.example.gsi.Entity.createAlergia(
                                                    ""
                                                )
                                            )
                                                .enqueue(object : Callback<Alergia> {
                                                    override fun onResponse(
                                                        call: Call<Alergia>,
                                                        response: Response<Alergia>
                                                    ) {
                                                        val idA = putAlergia(response.body()?.id!!)
                                                        Constant.retrofit.createUsuario(
                                                            usuarioPaciente
                                                        )
                                                            .enqueue(object : Callback<Usuario> {
                                                                override fun onResponse(
                                                                    call: Call<Usuario>,
                                                                    response: Response<Usuario>
                                                                ) {
                                                                    val idU =
                                                                        putUsuario(response.body()?.id!!)
                                                                    val pais =
                                                                        createPais(binding.spPais.selectedItemPosition.toLong() + 1)
                                                                    val estadoCivil =
                                                                        createEstadoCivil(binding.spEstadoCivil.selectedItemPosition.toLong() + 1)
                                                                    val sexo =
                                                                        createSexo(binding.spSexo.selectedItemPosition.toLong() + 1)
                                                                    val pac =
                                                                        com.example.gsi.Entity.createPaciente(
                                                                            binding.editTextTexNombre.text.toString(),
                                                                            binding.ediTextApePaterno.text.toString(),
                                                                            binding.ediTextApeMaterno.text.toString(),
                                                                            binding.editTextDni.text.toString()
                                                                                .toInt(),
                                                                            binding.editTextDireccion.text.toString(),
                                                                            binding.editTextTelefono.text.toString(),
                                                                            binding.editTexEmail.text.toString(),
                                                                            pais,
                                                                            estadoCivil,
                                                                            sexo,
                                                                            idU,
                                                                            idCE,
                                                                            idCM,
                                                                            idE,
                                                                            idM,
                                                                            idA
                                                                        )
                                                                    Constant.retrofit.createPaciente(
                                                                        pac
                                                                    ).enqueue(object :
                                                                        Callback<Paciente> {
                                                                        override fun onResponse(
                                                                            call: Call<Paciente>,
                                                                            response: Response<Paciente>
                                                                        ) {
                                                                            Log.e(
                                                                                "Paciente",
                                                                                response.body()
                                                                                    .toString()
                                                                            )
                                                                        }
                                                                        override fun onFailure(
                                                                            call: Call<Paciente>,
                                                                            t: Throwable
                                                                        ) {
                                                                        }
                                                                    })
                                                                }
                                                                override fun onFailure(
                                                                    call: Call<Usuario>,
                                                                    t: Throwable
                                                                ) {
                                                                }
                                                            })
                                                    }
                                                    override fun onFailure(
                                                        call: Call<Alergia>,
                                                        t: Throwable
                                                    ) {
                                                    }
                                                }
                                                )
                                        }
                                        override fun onFailure(call: Call<Medicina>, t: Throwable) {
                                        }
                                    })
                                }
                                override fun onFailure(call: Call<Enfermedad>, t: Throwable) {
                                }
                            })
                        }
                        override fun onFailure(call: Call<ContactoMedico>, t: Throwable) {
                        }
                    })
                }
                override fun onFailure(call: Call<ContactoEmergencia>, t: Throwable) {
                }
            }
            )
    }
}






