package com.example.gsi.Service

import com.example.gsi.Entity.Alergia
import com.example.gsi.Entity.Alergias
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Url

interface Services {

    @GET("alergia/listar")
     fun getAllAlergias():Call<List<Alergia>>

}