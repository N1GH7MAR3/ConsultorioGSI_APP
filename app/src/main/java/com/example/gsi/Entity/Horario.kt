package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.sql.Time
import java.text.SimpleDateFormat
import java.time.LocalTime

data class Horario(
    @SerializedName("id")
    @Expose
    val id: Long,

    @SerializedName("horaingreso")
    @Expose
    val horaingreso: String,

    @SerializedName("horasalida")
    @Expose
    val horasalida: String

)
