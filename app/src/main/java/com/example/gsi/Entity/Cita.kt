package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.util.*

data class Cita(
    @SerializedName("id")
    @Expose
    val id:Long,
    @SerializedName("fecharegistro")
    @Expose
    val fecharegistro:Date,
    @SerializedName("fechacita")
    @Expose
    val fechacita:Date,
    @SerializedName("medico")
    @Expose
    val medico:Medico,
    @SerializedName("paciente")
    @Expose
    val paciente:Paciente,
    @SerializedName("especialidad")
    @Expose
    val especialidad:Especialidad,
    @SerializedName("procedimiento")
    @Expose
    val procedimiento:Procedimiento
)
