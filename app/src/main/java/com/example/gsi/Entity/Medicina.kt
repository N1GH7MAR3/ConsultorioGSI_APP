package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class createMedicina(
    @SerializedName("descripcion")
    @Expose
    val descripcion: String
)
data class putMedicina(
    @SerializedName("id")
    @Expose
    val id: Long)
data class Medicina(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("descripcion")
    @Expose
    val descripcion: String
)
