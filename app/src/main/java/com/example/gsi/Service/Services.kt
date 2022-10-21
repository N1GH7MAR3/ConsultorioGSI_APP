package com.example.gsi.Service

import android.service.autofill.UserData
import com.example.gsi.Entity.Alergia
import com.example.gsi.Entity.Alergias
import com.example.gsi.Entity.Usuario
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Services {

    @GET("alergia/listar")
     fun getAllAlergias():Call<List<Alergia>>

     @POST("usuario/buscaruser")
     fun VerifyUser(@Body body: Usuario):Call<Usuario>

}