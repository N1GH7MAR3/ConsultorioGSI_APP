package com.example.gsi.Constans

import com.example.gsi.Retrofit.RetrofitHelper
import com.example.gsi.Service.ApiService
import com.example.gsi.Service.Services

object Constant {
<<<<<<< HEAD
       val BASE_URL="http://192.168.1.4:8093/consultoriogsi/"
=======
       val BASE_URL="http://192.168.1.5:8093/consultoriogsi/"
>>>>>>> e228467cf4ff606a8f595211808f6132fcdc99a8
     val NoInternet="Connection Error"
     val retrofit= RetrofitHelper.getRetrofit().create(Services::class.java)
    val api= ApiService()
 }

