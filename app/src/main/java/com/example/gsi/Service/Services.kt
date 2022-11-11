package com.example.gsi.Service

import android.service.autofill.UserData
import com.example.gsi.Entity.*

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface Services {
    @GET("pais/listar")
    fun getAllPais(): Call<List<Pais>>

    @GET("estadocivil/listar")
    fun getAllEstadoCivil(): Call<List<EstadoCivil>>

    @GET("sexo/listar")
    fun getAllSexo(): Call<List<Sexo>>

    @Headers("Accept: application/json")
    @GET("medico/listar")
    fun getAllMedico(): Call<List<Medico>>

    @GET("especialidad/listar")
    fun getAllEspecialidades(): Call<List<Especialidad>>

    @GET("cita/buscardni/{dni}")
    fun getCitasPaciente(@Path("dni") dni: Int): Call<List<Cita>>

    @GET("procedimiento/listar")
    fun getAllProcedimientos(): Call<List<Procedimiento>>

    @GET("procedimiento/buscarxEspecialidad/{nombre}")
    fun getProcedimientoxEspecialidad(@Path("nombre") nombre: String): Call<List<Procedimiento>>

    @GET("medico/buscarxEspecialidad/{nombre}")
    fun getMedicoxEspecialidad(@Path("nombre") nombre: String): Call<List<Medico>>

    @POST("usuario/verifyuser")
    fun verifyUser(@Body body: ValidateUsuario): Call<Usuario>

    @POST("paciente/buscaruser")
    fun searchPaciente(@Body body: SearchUsuario): Call<Paciente>

    @Headers("Content-Type:application/json")
    @PUT("especialidad/editar/{id}")
    fun updateEspecialidad(@Path("id") id: Long, @Body body: Especialidad): Call<Especialidad>

    @DELETE("especialidad/borrar/{id}")
    fun deleteEspecialidad(@Path("id") id: Long): Call<Especialidad>

    @POST("especialidad/registrar")
    fun createEspecialidad(@Body body: createEspecialidad): Call<createEspecialidad>

    @POST("medico/registrar")
    fun createMedico(@Body body: createMedico): Call<createMedico>

    @POST("paciente/registrar")
    fun createPaciente(@Body body: createPaciente): Call<Paciente>

    @POST("usuario/registrar")
    fun createUsuario(@Body body: createUsuarioPaciente): Call<Usuario>

    @POST("alergia/registrar")
    fun createAlergia(@Body body: createAlergia): Call<Alergia>

    @POST("contactoemergencia/registrar")
    fun createContactoEmergencia(@Body body: createContactoEmergencia): Call<ContactoEmergencia>

    @POST("contactomedico/registrar")
    fun createContactoMedico(@Body body: createContactoMedico): Call<ContactoMedico>

    @POST("enfermedad/registrar")
    fun createEnfermedad(@Body body: createEnfermedad): Call<Enfermedad>

    @POST("medicina/registrar")
    fun createMedicina(@Body body: createMedicina): Call<Medicina>


}