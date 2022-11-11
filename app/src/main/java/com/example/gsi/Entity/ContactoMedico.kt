package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class createContactoMedico(
    @SerializedName("descripcion")
    @Expose
    val descripcion: String
)
data class putContactoMedico(
    @SerializedName("id")
    @Expose
    val id: Long)
data class ContactoMedico(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("descripcion")
    @Expose
    val descripcion: String
)
