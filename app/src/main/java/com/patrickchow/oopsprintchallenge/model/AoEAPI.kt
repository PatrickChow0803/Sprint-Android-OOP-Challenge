package com.patrickchow.oopsprintchallenge.model

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit

interface AoEAPI {

    @GET("/civilization/{id}")
    fun getCivilization(@Path("id") id: Int): Call<Civilization>

    @GET("/unit/{id}")
    fun getUnit(@Path("id") id: Int): Call<Unit>

    @GET("/structure/{id}")
    fun getStructure(@Path("id") id: Int): Call<Structure>

    @GET("/technology/{id}")
    fun getTechnology(@Path("id") id: Int): Call<Technology>

    class Factory{
        companion object{
            private const val BASE_URL = "https://age-of-empires-2-api.herokuapp.com/api/v1"

            fun create():AoEAPI{
                val logger = HttpLoggingInterceptor()
                logger.level = HttpLoggingInterceptor.Level.BASIC

                val okHttpClient = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .retryOnConnectionFailure(false)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .build()

                val gson = GsonBuilder()
                    .setLenient()
                    .create()

                val retrofit = Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()

                return retrofit.create(AoEAPI::class.java)
            }
        }
    }
}