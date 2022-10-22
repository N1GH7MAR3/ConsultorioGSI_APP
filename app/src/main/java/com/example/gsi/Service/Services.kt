package com.example.gsi.Service

import android.service.autofill.UserData
import com.example.gsi.Entity.*

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Services {

    @GET("alergia/listar")
     fun getAllAlergias():Call<List<Alergia>>

    @POST("usuario/verifyuser")
    fun verifyUser(@Body body: ValidateUsuario):Call<Usuario>
    @POST("paciente/buscaruser")
    fun searchPaciente(@Body body: SearchUsuario):Call<Paciente>

}