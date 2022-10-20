package com.example.gsi.Retrofit

import com.example.gsi.Constans.Constant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


class RetrofitHelper {
     companion object{
    fun getRetrofit():Retrofit{
        val interceptor=HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        val cliente=OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit= Retrofit.Builder()
            .baseUrl("http://192.168.1.4:8093/consultoriogsi/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(cliente)
            .build();
        return retrofit

    }}
}