package com.example.weatherapp

import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response

class repository {
    companion object {
         fun getWeather(city: String): Call<JsonObject> {
            return RetrofitInstance.api.getWeather(city)
        }
    }
}