package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class putProcedimiento(
    @SerializedName("id")
    @Expose
    val id: Long)
data class createProcedimiento(
    @SerializedName("nombre")
    @Expose
    val nombre: String,
    @SerializedName("especialidad")
    @Expose
    val especialidad: putEspecialidad
)
data class Procedimiento(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("nombre")
    @Expose
    val nombre: String,
    @SerializedName("especialidad")
    @Expose
    val especialidad: Especialidad
)
