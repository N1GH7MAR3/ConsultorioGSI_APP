package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Timestamp
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDateTime
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
    val fechacita:String,
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
