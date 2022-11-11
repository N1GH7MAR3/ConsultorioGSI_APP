package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class createPaciente(
    @SerializedName("nombre")
    @Expose
    val nombre:String,
    @SerializedName("apellido_paterno")
    @Expose
    val apellido_paterno:String,
    @SerializedName("apellido_materno")
    @Expose
    val apellido_materno:String,
    @SerializedName("dni")
    @Expose
    val dni:Int,
    @SerializedName("direccion")
    @Expose
    val direccion:String,
    @SerializedName("telefono")
    @Expose
    val telefono:String,
    @SerializedName("correo")
    @Expose
    val correo:String,
    @SerializedName("pais")
    @Expose
    val pais:createPais,
    @SerializedName("estadoCivil")
    @Expose
    val estadoCivil:createEstadoCivil,
    @SerializedName("sexo")
    @Expose
    val sexo:createSexo,
    @SerializedName("usuario")
    @Expose
    val usuario:putUsuario,
    @SerializedName("contactoEmergencia")
    @Expose
    val contactoEmergencia:putContactoEmergencia,
    @SerializedName("contactoMedico")
    @Expose
    val contactoMedico:putContactoMedico,
    @SerializedName("enfermedad")
    @Expose
    val enfermedad:putEnfermedad,
    @SerializedName("medicina")
    @Expose
    val medicina:putMedicina,
    @SerializedName("alergia")
    @Expose
    val alergia:putAlergia
)
data class Paciente(
    @SerializedName("id")
    @Expose
    val id:Long,
    @SerializedName("nombre")
    @Expose
    val nombre:String,
    @SerializedName("apellido_paterno")
    @Expose
    val apellido_paterno:String,
    @SerializedName("apellido_materno")
    @Expose
    val apellido_materno:String,
    @SerializedName("dni")
    @Expose
    val dni:Int,
    @SerializedName("direccion")
    @Expose
    val direccion:String,
    @SerializedName("telefono")
    @Expose
    val telefono:String,
    @SerializedName("correo")
    @Expose
    val correo:String,
    @SerializedName("pais")
    @Expose
    val pais:Pais,
    @SerializedName("estadoCivil")
    @Expose
    val estadoCivil:EstadoCivil,
    @SerializedName("sexo")
    @Expose
    val sexo:Sexo,
    @SerializedName("usuario")
    @Expose
    val usuario:Usuario,
    @SerializedName("contactoEmergencia")
    @Expose
    val contactoEmergencia:ContactoEmergencia,
    @SerializedName("contactoMedico")
    @Expose
    val contactoMedico:ContactoMedico,
    @SerializedName("enfermedad")
    @Expose
    val enfermedad:Enfermedad,
    @SerializedName("medicina")
    @Expose
    val medicina:Medicina,
    @SerializedName("alergia")
    @Expose
    val alergia:Alergia

)
