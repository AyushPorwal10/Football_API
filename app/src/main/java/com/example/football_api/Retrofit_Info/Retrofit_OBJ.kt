package com.example.football_api.Retrofit_Info

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit_OBJ {


    val BASE_URL = "https://football-highlights-api.p.rapidapi.com/"
    // add api to get app run

    private const val API_HOST = "football-highlights-api.p.rapidapi.com"
    fun getInstance(): Retrofit {

        val interceptor = Interceptor { chain ->
            val original: Request = chain.request()
            val requestBuilder = original.newBuilder()
                .addHeader("x-rapidapi-key", API_KEY)
                .addHeader("x-rapidapi-host", API_HOST)

            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }

        // Build the OkHttpClient with the interceptor
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()


        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}