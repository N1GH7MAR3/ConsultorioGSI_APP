package com.example.gsi.Retrofit

import com.example.gsi.Constans.Constant
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit


class RetrofitHelper {

     companion object{
         fun getRetrofit(): Retrofit {
             val gson: Gson = GsonBuilder().apply {
                 setDateFormat("yyyy-MM-dd HH:mm:ss")
             }.create()
             val interceptor = HttpLoggingInterceptor()
             interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
             val cliente = OkHttpClient.Builder()
                 .addInterceptor(interceptor)
                 .connectTimeout(1, TimeUnit.MINUTES)
                 .readTimeout(1, TimeUnit.MINUTES)
                 .build()

             return Retrofit.Builder()
                 .baseUrl(Constant.BASE_URL)
                 .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                 .addConverterFactory(GsonConverterFactory.create())
                 .client(cliente)
                 .apply { gson }.build()

         }

     }
}