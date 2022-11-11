package com.example.gsi.Entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
data class createUsuarioPaciente(
    @SerializedName("usuario")
    @Expose
    val usuario: String,
    @SerializedName("contraseña")
    @Expose
    val contraseña:String,
    @SerializedName("rol")
    @Expose
    val rol:createRolUsuario)
data class putUsuario(
    @SerializedName("id")
    @Expose
    val id:Long)
data class Usuario(
    @SerializedName("id")
    @Expose
    val id:Long,
    @SerializedName("usuario")
    @Expose
    val usuario: String,
    @SerializedName("contraseña")
    @Expose
    val contraseña:String,
    @SerializedName("rol")
    @Expose
    val rol:Rol
)
data class SearchUsuario(
    @SerializedName("usuario")
    @Expose
    val usuario: String
)
data class ValidateUsuario(

    @SerializedName("usuario")
    @Expose
    val usuario: String,
    @SerializedName("contraseña")
    @Expose
    val contraseña:String)
