package com.example.gsi.Constans

import com.example.gsi.Retrofit.RetrofitHelper
import com.example.gsi.Service.ApiService
import com.example.gsi.Service.Services

object Constant {


    val BASE_URL = "http://192.168.1.4:8093/consultoriogsi/"

    val NoInternet = "Connection Error"
    val retrofit = RetrofitHelper.getRetrofit().create(Services::class.java)
    val api = ApiService()
}

