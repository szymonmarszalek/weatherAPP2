package com.example.weatherapp

data class Weather(
    val city: String,
    val icon: String,
    val sunrise: String,
    val sunset: String ,
    val temperature: String,
    val description: String,
    val pressure: String
)