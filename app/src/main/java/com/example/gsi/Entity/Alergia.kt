package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class putAlergia(
    @SerializedName("id")
    @Expose
    val id:Long)
data class createAlergia(
    @SerializedName("descripcion")
    @Expose
    val descripcion:String)
data class Alergia(
    @SerializedName("id")
    @Expose
    val id:Long,
    @SerializedName("descripcion")
    @Expose
    val descripcion:String

)
