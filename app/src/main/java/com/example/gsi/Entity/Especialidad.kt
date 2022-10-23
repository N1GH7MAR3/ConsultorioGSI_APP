package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class Especialidad(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("nombre")
    @Expose
    val nombre: String,
    @SerializedName("image")
    @Expose
    val image:String
)
