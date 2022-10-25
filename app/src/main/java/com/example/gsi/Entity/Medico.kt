package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Medicos(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("nombre")
    @Expose
    val nombre: String,
    @SerializedName("apellido_paterno")
    @Expose
    val apellido_paterno: String,
    @SerializedName("apellido_materno")
    @Expose
    val apellido_materno: String,
    @SerializedName("dni")
    @Expose
    val dni: String,
    @SerializedName("pais")
    @Expose
    val pais: Pais,
    @SerializedName("estadoCivil")
    @Expose
    val estadoCivil: EstadoCivil,
    @SerializedName("sexo")
    @Expose
    val sexo: Sexo,
    @SerializedName("turno")
    @Expose
    val turno: Turno
)

data class Medico(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("nombre")
    @Expose
    val nombre: String,
    @SerializedName("apellido_paterno")
    @Expose
    val apellido_paterno: String,
    @SerializedName("apellido_materno")
    @Expose
    val apellido_materno: String,
    @SerializedName("dni")
    @Expose
    val dni: String,
    @SerializedName("pais")
    @Expose
    val pais: Pais,
    @SerializedName("estadoCivil")
    @Expose
    val estadoCivil: EstadoCivil,
    @SerializedName("sexo")
    @Expose
    val sexo: Sexo,
    @SerializedName("turno")
    @Expose
    val turno: Turno,

    @SerializedName("horario")
    @Expose
    val horario: Horario,
    @SerializedName("especialidad")
    @Expose
    val especialidad: Especialidad


)
