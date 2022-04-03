package com.example.weatherapp

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class MainPageViewModel: ViewModel() {
    val service: Service = Service()
    private val _weatherResp:MutableLiveData<Weather> = MutableLiveData()
    val weatherResp:LiveData<Weather>
        get()
        { return _weatherResp}
    //var temp = JSONObject(repositoryweather.execute().body().toString())
    var cityMain = "Gliwice"
    var weatherObj = MutableLiveData<Weather>()
    var time = MutableLiveData<String>()
    var time2 = MutableLiveData<Int>(1)
    fun increase(){
        time2.postValue(time2.value?.plus(1))
    }
    fun getWeather(city: String, context: Context)
    {
        viewModelScope.launch(Dispatchers.IO) {
                val weather = service.getWeather(city)
                if(weather != null) {
                    _weatherResp.postValue(weather!!)
                    cityMain = weather.city
                }
            else {
                val text = "Nie znaleziono takiego miasta"
                val duration = Toast.LENGTH_SHORT
                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
        }
    }
    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            while (true) {
                delay(1000)
                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault())
                val currentDate = sdf.format(Date())
                time.postValue(currentDate)
            }
        }
    }
}