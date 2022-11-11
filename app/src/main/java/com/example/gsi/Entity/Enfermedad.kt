package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class createEnfermedad(
    @SerializedName("descripcion")
    @Expose
    val descripcion: String
)
data class putEnfermedad(
    @SerializedName("id")
    @Expose
    val id: Long)
data class Enfermedad(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("descripcion")
    @Expose
    val descripcion: String
)
