package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class putEstadoCivil(
    @SerializedName("id")
    @Expose
    val id: Long)
data class EstadoCivil(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("nombre")
    @Expose
    val nombre: String
)
