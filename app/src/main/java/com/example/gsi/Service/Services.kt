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

    @GET("turno/listar")
    fun getAllTurno(): Call<List<Turno>>

    @GET("medico/listar")
    fun getAllMedico(): Call<List<Medico>>

    @GET("medico_procedimiento/listarpxm/{id}")
    fun getAllProcedimientosxMedico(@Path("id") id: Long): Call<List<Procedimiento>>

    @GET("medico_procedimiento/listarmxp/{id}")
    fun getAllMedicosxProcedimiento(@Path("id") id: Long): Call<List<Medico>>

    @GET("especialidad/listar")
    fun getAllEspecialidades(): Call<List<Especialidad>>

    @GET("procedimiento/listar")
    fun getAllProcedimientos(): Call<List<Procedimiento>>

    @GET("paciente/listar")
    fun getAllPacientes(): Call<List<Paciente>>

    @GET("cita/buscardni/{dni}")
    fun getCitasPaciente(@Path("dni") dni: Int): Call<List<Cita>>

    @GET("procedimiento/buscarxEspecialidad/{nombre}")
    fun getProcedimientoxEspecialidad(@Path("nombre") nombre: String): Call<List<Procedimiento>>

    @GET("medico/buscarxEspecialidad/{nombre}")
    fun getMedicoxEspecialidad(@Path("nombre") nombre: String): Call<List<Medico>>

    @POST("usuario/verifyuser")
    fun verifyUser(@Body body: ValidateUsuario): Call<Usuario>

    @POST("paciente/buscaruser")
    fun searchPaciente(@Body searchUsuario: SearchUsuario): Call<Paciente>

    @PUT("especialidad/editar/{id}")
    fun updateEspecialidad(@Path("id") id: Long, @Body body: Especialidad): Call<Especialidad>

    @DELETE("especialidad/borrar/{id}")
    fun deleteEspecialidad(@Path("id") id: Long): Call<Especialidad>

    @DELETE("medico/borrar/{id}")
    fun deleteMedico(@Path("id") id: Long): Call<Medico>

    @DELETE("procedimiento/borrar/{id}")
    fun deleteProcedimiento(@Path("id") id: Long): Call<Procedimiento>

    @POST("especialidad/registrar")
    fun createEspecialidad(@Body body: createEspecialidad): Call<createEspecialidad>

    @POST("medico/registrar")
    fun createMedico(@Body body: createMedico): Call<createMedico>

    @PUT("medico/editar/{id}")
    fun updateMedico(@Path("id") id: Long, @Body body: createMedico): Call<Medico>

    @PUT("procedimiento/editar/{id}")
    fun updateProcedimiento(@Path("id") id: Long, @Body body: createProcedimiento): Call<Procedimiento>

    @POST("procedimiento/registrar")
    fun createProcedimiento(@Body body: createProcedimiento): Call<createProcedimiento>

    @POST("paciente/registrar")
    fun createPaciente(@Body body: createPaciente): Call<Paciente>

    @PUT("paciente/editar/{id}")
    fun updatePaciente(@Path("id") id: Long, @Body body: updatePaciente): Call<Paciente>



    @POST("usuario/registrar")
    fun createUsuario(@Body body: createUsuarioPaciente): Call<Usuario>

    @PUT("usuario/editar/{id}")
    fun updateUsuario(@Path("id") id: Long, @Body body: createUsuarioPaciente): Call<Usuario>

    @POST("alergia/registrar")
    fun createAlergia(@Body body: createAlergia): Call<Alergia>

    @PUT("alergia/editar/{id}")
    fun updateAlergia(@Path("id") id: Long, @Body alergia: createAlergia): Call<Alergia>

    @POST("contactoemergencia/registrar")
    fun createContactoEmergencia(@Body body: createContactoEmergencia): Call<ContactoEmergencia>

    @PUT("contactoemergencia/editar/{id}")
    fun updateContactoEmergencia(
        @Path("id") id: Long,
        @Body contactoEmergencia: createContactoEmergencia
    ): Call<ContactoEmergencia>

    @POST("contactomedico/registrar")
    fun createContactoMedico(@Body body: createContactoMedico): Call<ContactoMedico>

    @PUT("contactomedico/editar/{id}")
    fun updateContactoMedico(
        @Path("id") id: Long,
        @Body contactoMedico: createContactoMedico
    ): Call<ContactoMedico>

    @POST("enfermedad/registrar")
    fun createEnfermedad(@Body body: createEnfermedad): Call<Enfermedad>

    @PUT("enfermedad/editar/{id}")
    fun updateEnfermedad(@Path("id") id: Long, @Body enfermedad: createEnfermedad): Call<Enfermedad>

    @POST("medicina/registrar")
    fun createMedicina(@Body body: createMedicina): Call<Medicina>

    @PUT("medicina/editar/{id}")
    fun updateMedicina(@Path("id") id: Long, @Body medicina: createMedicina): Call<Medicina>

    @POST("cita/registrar")
    fun createCita(@Body body:createCita):Call<Cita>

    @GET("cita/buscarcita/{fechacita}/{idespecialidad}/{idmedico}/{idprocedimiento}")
    fun searchCitas(@Path("fechacita")fechacita:String,@Path("idespecialidad")idespecialidad:Long,
                    @Path("idmedico")idmedico:Long,@Path("idprocedimiento")idprocedimiento:Long):Call <Cita>
}