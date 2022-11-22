package com.example.gsi.Constans

import com.example.gsi.Retrofit.RetrofitHelper
import com.example.gsi.Service.ApiService
import com.example.gsi.Service.Services

object Constant {
<<<<<<< HEAD

    val BASE_URL = "http://192.168.1.7:8093/consultoriogsi/"
=======
    val BASE_URL = "http://192.168.1.5:8093/consultoriogsi/"
>>>>>>> 4e68f06a6ed447f61a2d46f2e1346f1278832bf1

    val NoInternet = "Connection Error"
    val retrofit = RetrofitHelper.getRetrofit().create(Services::class.java)
    val api = ApiService()
}

