package com.example.weatherapp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

class Service {

       fun getWeather(city: String): Weather? {
         try {
           val weather = repository.getWeather(city)
           val jsonWeather = JSONObject(weather.execute().body().toString())
           val jsonMain = jsonWeather.getJSONObject("main")
           val weatherJson =
             jsonWeather.getJSONArray("weather").getJSONObject(0).getString("description")
           val icon = jsonWeather.getJSONArray("weather").getJSONObject(0).getString("icon")
           val sysObject = jsonWeather.getJSONObject("sys")
           val temp = jsonMain.getString("temp")
           val pressure = jsonMain.getString("pressure")
           val sunrise = sysObject.getString("sunrise").toLong()
           val sunset = sysObject.getString("sunset").toLong()
           val sunriseRet = SimpleDateFormat("hh:mm a", Locale.GERMANY).format(Date(sunrise * 1000))
           val sunsetRet = SimpleDateFormat("hh:mm a", Locale.GERMANY).format(Date(sunset * 1000))
           val ObjectWeather =
             Weather(city, icon, sunriseRet, sunsetRet, temp, weatherJson, pressure)
           return ObjectWeather
         }
         catch(e: ExceptionInInitializerError) {
           return null
         }
     }
}
