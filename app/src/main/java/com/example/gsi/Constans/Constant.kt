package com.example.gsi.Constans

import com.example.gsi.Retrofit.RetrofitHelper
import com.example.gsi.Service.ApiService
import com.example.gsi.Service.Services

object Constant {
<<<<<<< HEAD
    val BASE_URL = "http://192.168.1.7:8093/consultoriogsi/"
=======
    val BASE_URL = "http://192.168.1.5:8093/consultoriogsi/"
>>>>>>> e8c91a008516a93e20d0c86cdb95b45cd2e4cf63
    val NoInternet = "Connection Error"
    val retrofit = RetrofitHelper.getRetrofit().create(Services::class.java)
    val api = ApiService()
}

