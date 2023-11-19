package com.losextraditados.appproductos.src.service

import com.losextraditados.appproductos.src.data.LoginData
import com.losextraditados.appproductos.src.reponses.ApiResLogin
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiSerLogin {
    @POST("user/login") // Ruta relativa a la base URL del servidor
    fun loginUser(@Body loginData: LoginData): Call<ApiResLogin>
}