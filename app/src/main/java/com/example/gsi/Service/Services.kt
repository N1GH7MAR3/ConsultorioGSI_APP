package com.example.gsi.Service

import android.service.autofill.UserData
import com.example.gsi.Entity.*

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Services {
    @GET("pais/listar")
    fun getAllPais():Call<List<Pais>>
    @GET("estadocivil/listar")
    fun getAllEstadoCivil():Call<List<Pais>>
    @GET("sexo/listar")
    fun getAllSexo():Call<List<Pais>>
    @Headers("Accept: application/json")
    @GET("medico/listar")
    fun getAllMedico():Call<List<Medico>>
    @GET("especialidad/listar")
    fun getAllEspecialidades():Call<List<Especialidad>>
    @POST("usuario/verifyuser")
    fun verifyUser(@Body body: ValidateUsuario):Call<Usuario>
    @POST("paciente/buscaruser")
    fun searchPaciente(@Body body: SearchUsuario):Call<Paciente>
    @Headers("Content-Type:application/json")
    @PUT("especialidad/editar/{id}")
    fun updateEspecialidad(@Path("id")id:Long, @Body body: Especialidad):Call<Especialidad>
    @DELETE("especialidad/borrar/{id}")
    fun deleteEspecialidad(@Path("id")id:Long):Call<Especialidad>
    @POST("especialidad/registrar")
    fun createEspecialidad(@Body body: createEspecialidad):Call<createEspecialidad>





}