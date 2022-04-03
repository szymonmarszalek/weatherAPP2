package com.example.weatherapp

import com.google.gson.JsonObject
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService  {

    @GET("weather")
    fun getWeather(@Query("q") city: String = "rybnik",
                  @Query("units") units: String = "metric",
                  @Query("APPID") appid: String = "6993405572a7fcb22034d2943e3133ce") : Call<JsonObject>

}